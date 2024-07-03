package com.luizafmartinez.m25_threadscoroutinesrest_restful.api.model

data class Foto(
    val albumId: Int,
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
)