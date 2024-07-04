package com.luizafmartinez.m25_threadscoroutinesrest_restful.api

import com.luizafmartinez.m25_threadscoroutinesrest_restful.api.model.Filme
import com.luizafmartinez.m25_threadscoroutinesrest_restful.api.model.FilmeResposta
import com.luizafmartinez.m25_threadscoroutinesrest_restful.api.model.Postagem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface FilmeAPI {

    @GET("movie/popular")
    suspend fun recuperarFilmesPopulares(): Response<FilmeResposta>

}


