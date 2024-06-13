package com.luizafmartinez.m25_threadscoroutinesrest_restful.api

import retrofit2.http.GET

interface EnderecoAPI {

    // https://viacep.com.br/ws/01001000/json/
    // Base URL: https://viacep.com.br/  +  ws/01001000/json/
    @GET("ws/01001000/json/")
    suspend fun recuperarEndereco()

}