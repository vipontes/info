package br.net.easify.info.View.News

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import br.net.easify.info.Model.News
import br.net.easify.info.R
import br.net.easify.info.View.NewsDetail.NewsDetailActivity
import br.net.easify.info.ViewModel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_news.*

class NewsFragment : Fragment() {

    private lateinit var viewModel: NewsViewModel
    private var listAdapter = NewsAdapter(arrayListOf())

    private val newsDataObserver = Observer<News> { res: News ->
        res?.let {
            newsList.visibility = View.VISIBLE
            listAdapter.updateNews(res.articles)
        }
    }

    private val loadingLiveDataObserver = Observer<Boolean> { isLoading: Boolean ->
        loadingView.visibility = if(isLoading) View.VISIBLE else View.GONE
        if ( isLoading ) {
            listError.visibility = View.GONE
            newsList.visibility = View.GONE
        }
    }

    private val loadErrorLiveDataObserver = Observer<Boolean> { isError: Boolean ->
        listError.visibility = if(isError) View.VISIBLE else View.GONE
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
        viewModel.news.observe(this, newsDataObserver)
        viewModel.loading.observe(this, loadingLiveDataObserver)
        viewModel.loadError.observe(this, loadErrorLiveDataObserver)
        viewModel.refresh()

        newsList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }

        listAdapter.onItemClick = { article ->

            context?.let {
                val intent = NewsDetailActivity.newIntent(it.applicationContext, article)
                startActivity(intent)
            }

        }

        refreshLayout.setOnRefreshListener {

            newsList.visibility = View.GONE
            listError.visibility = View.GONE
            loadingView.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayout.isRefreshing = false
        }
    }

}
