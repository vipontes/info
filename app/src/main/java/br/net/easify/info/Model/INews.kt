package br.net.easify.info.Model

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface INews {

    @GET("top-headlines")
    fun getTopHeadlines(@Query("country") country: String,
                        @Query("sortBy") sortBy: String,
                        @Query("apiKey") key: String): Single<News>
}