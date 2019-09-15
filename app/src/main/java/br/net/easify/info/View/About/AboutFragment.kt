package br.net.easify.info.View.About


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.net.easify.info.R
import kotlinx.android.synthetic.main.activity_news_detail.*
import kotlinx.android.synthetic.main.fragment_about.*


class AboutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        linkedin.setOnClickListener(View.OnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://www.linkedin.com/in/vinicius-pontes-8b152383/")
            startActivity(openURL)
        })

        github.setOnClickListener(View.OnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://github.com/vipontes")
            startActivity(openURL)
        })
    }
}
