package com.luizafmartinez.m25_threadscoroutinesrest_restful.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {

    companion object {

        const val APIGO = "8757e6fe8d068d87174a9fd7d132912a"
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val BASE_URL_IMAGE = "https://image.tmdb.org/t/p/"

        val apiViaCEP = Retrofit.Builder()
            .baseUrl("https://viacep.com.br/")
            .addConverterFactory( GsonConverterFactory.create() ) // Para JSON ou XML
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory( GsonConverterFactory.create() ) // Para JSON ou XML
            .build()

        val filmeAPI = Retrofit.Builder()
            .baseUrl(BASE_URL) // 3 = versão da API
            .addConverterFactory( GsonConverterFactory.create() ) // Para JSON ou XML
            .build()
            .create( FilmeAPI::class.java )

    }
}