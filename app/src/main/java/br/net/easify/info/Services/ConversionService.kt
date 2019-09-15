package br.net.easify.info.Services

import br.net.easify.info.Interfaces.ICurrency
import br.net.easify.info.Model.Conversion
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ConversionService {
    private val BASE_URL = "https://economia.awesomeapi.com.br/"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(ICurrency::class.java)

    fun getAll(): Single<Conversion> {
        return api.getAll()
    }
}