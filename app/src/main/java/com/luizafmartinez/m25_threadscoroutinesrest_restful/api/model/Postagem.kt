package com.luizafmartinez.m25_threadscoroutinesrest_restful.api.model

import com.google.gson.annotations.SerializedName

data class Postagem(
    //@SerializedName("body")
    //val description: String,
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)