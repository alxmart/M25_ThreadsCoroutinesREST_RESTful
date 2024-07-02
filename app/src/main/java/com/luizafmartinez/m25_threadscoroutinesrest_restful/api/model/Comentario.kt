package com.luizafmartinez.m25_threadscoroutinesrest_restful.api.model

data class Comentario(
    val body: String,
    val email: String,
    val id: Int,
    val name: String,
    val postId: Int
)