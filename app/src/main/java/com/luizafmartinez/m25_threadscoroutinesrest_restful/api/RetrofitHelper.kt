package com.luizafmartinez.m25_threadscoroutinesrest_restful.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {

    companion object {

        val apiViaCEP = Retrofit.Builder()
            .baseUrl("https://viacep.com.br/")
            .addConverterFactory( GsonConverterFactory.create() ) // Para JSON ou XML
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory( GsonConverterFactory.create() ) // Para JSON ou XML
            .build()

        val filmeAPI = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/") // 3 = versão da API
            .addConverterFactory( GsonConverterFactory.create() ) // Para JSON ou XML
            .build()
            .create( FilmeAPI::class.java )

    }
}