package br.net.easify.info.View.NewsDetail

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import br.net.easify.info.Model.Articles
import br.net.easify.info.R
import br.net.easify.info.Utils.getProgressDrawable
import br.net.easify.info.Utils.loadImage
import kotlinx.android.synthetic.main.activity_news_detail.*

class NewsDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)

        val article: Articles = intent.getParcelableExtra(ARTICLE)
            ?: throw IllegalStateException("field $ARTICLE missing in Intent")

        newsImage.loadImage(article?.urlToImage, getProgressDrawable(this))
        newsTitle.text = article?.title
        newsDescription.text = article?.description
        newsContent.text = article?.content
        article.let {
            var content: String? = it.content
            var index: Int? = content?.indexOf('[')
            index.let {
                if (it != null && it!! > -1) {
                    content = content?.substring(0, it)
                }
            }
            newsContent.text = content
        }
        newsUrl.text = article?.url

        newsUrl.setOnClickListener(View.OnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse(article?.url)
            startActivity(openURL)
        })

        val actionbar = supportActionBar
        actionbar!!.title = article.title
        actionbar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        private val ARTICLE = "article"
        fun newIntent(context: Context, article: Articles): Intent {
            val intent = Intent(context, NewsDetailActivity::class.java)
            intent.putExtra(ARTICLE, article)
            return intent
        }
    }

}
