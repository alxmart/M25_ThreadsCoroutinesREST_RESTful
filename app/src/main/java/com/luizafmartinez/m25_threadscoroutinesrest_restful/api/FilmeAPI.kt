package com.luizafmartinez.m25_threadscoroutinesrest_restful.api

import com.luizafmartinez.m25_threadscoroutinesrest_restful.api.model.Filme
import com.luizafmartinez.m25_threadscoroutinesrest_restful.api.model.FilmeResposta
import com.luizafmartinez.m25_threadscoroutinesrest_restful.api.model.Postagem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface FilmeAPI {

    @GET("movie/popular?api_key=${RetrofitHelper.APIGO}")
    suspend fun recuperarFilmesPopulares(): Response<FilmeResposta>

    @GET("movie/{movie_id}?api_key=${RetrofitHelper.APIGO}")
    suspend fun recuperarDetalhesFilme(): Response<FilmeResposta>

}


