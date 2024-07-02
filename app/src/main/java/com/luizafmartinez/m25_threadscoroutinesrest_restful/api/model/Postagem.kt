package com.luizafmartinez.m25_threadscoroutinesrest_restful.api.model

import com.google.gson.annotations.SerializedName

data class Postagem(
    @SerializedName("body")
    val descricao: String,
    //val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)