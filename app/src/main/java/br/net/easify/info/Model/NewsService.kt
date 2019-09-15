package br.net.easify.info.Model

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NewsService {

    private val BASE_URL = "https://newsapi.org/v2/"
    private val API_KEY = "b6472250390d4f9489ffec633e7e624a"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(INews::class.java)

    fun getTopHeadlines(): Single<News> {
        return api.getTopHeadlines("br", "publishedAt", API_KEY)
    }
}