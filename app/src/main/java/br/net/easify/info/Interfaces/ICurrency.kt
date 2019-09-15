package br.net.easify.info.Interfaces

import br.net.easify.info.Model.Conversion
import io.reactivex.Single
import retrofit2.http.GET

interface ICurrency {
    @GET("all")
    fun getAll(): Single<Conversion>
}