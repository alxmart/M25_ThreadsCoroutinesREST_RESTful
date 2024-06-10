package com.luizafmartinez.m25_threadscoroutinesrest_restful.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {

    companion object {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://viacep.com.br/")
            .addConverterFactory( GsonConverterFactory.create() ) // Para JSON ou XML
            .build()
    }
}