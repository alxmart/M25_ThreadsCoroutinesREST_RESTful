package com.luizafmartinez.m25_threadscoroutinesrest_restful.api.model

import com.google.gson.annotations.SerializedName

data class Endereco(

    /*
    @SerializedName("cep")
    val teste: String,
    */
    val cep: String,
    val logradouro: String,
    val complemento: String,
    val bairro: String,
    val localidade: String,
    val uf: String
)
