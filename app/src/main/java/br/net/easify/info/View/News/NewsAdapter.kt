package br.net.easify.info.View.News

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import br.net.easify.info.Model.Articles
import br.net.easify.info.R
import br.net.easify.info.Utils.getProgressDrawable
import br.net.easify.info.Utils.loadImage
import kotlinx.android.synthetic.main.news_item.view.*

class NewsAdapter(private val articles: ArrayList<Articles>): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    var onItemClick: ((Articles) -> Unit)? = null

    fun updateNews(updatedNews: List<Articles>?) {
        articles.clear()
        updatedNews?.let { articles.addAll(it) }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount() = articles.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.view.title.text = articles[position].title
        holder.view.newsImage.loadImage(articles[position].urlToImage, getProgressDrawable(holder.view.context))
    }

    inner class NewsViewHolder(var view: View): RecyclerView.ViewHolder(view) {
        val newsImage: ImageView = view.findViewById(R.id.newsImage)

        init {
            view.setOnClickListener {
                onItemClick?.invoke(articles[adapterPosition])
            }
        }
    }
}
