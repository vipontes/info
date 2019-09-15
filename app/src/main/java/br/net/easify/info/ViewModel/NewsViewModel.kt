package br.net.easify.info.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.net.easify.info.Model.News
import br.net.easify.info.Services.NewsService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class NewsViewModel(application: Application) : AndroidViewModel(application) {

    val news by lazy { MutableLiveData<News>() }
    val loadError by lazy { MutableLiveData<Boolean>() }
    val loading by lazy { MutableLiveData<Boolean>() }

    private val newsService = NewsService()
    private val disposable = CompositeDisposable()

    fun refresh() {
        loading.value = true
        getTopHeadlines()
    }

    private fun getTopHeadlines() {
        disposable.add(
            newsService.getTopHeadlines()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<News>() {
                    override fun onSuccess(res: News) {
                        loading.value = false
                        news.value = res
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        loadError.value = true
                        loading.value = false
                        news.value = null
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()

        disposable.clear()
    }
}