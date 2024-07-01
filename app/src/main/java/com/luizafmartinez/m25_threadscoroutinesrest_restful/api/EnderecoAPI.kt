package com.luizafmartinez.m25_threadscoroutinesrest_restful.api

import retrofit2.Response
import retrofit2.http.GET

interface EnderecoAPI {
    // Base URL: https://viacep.com.br/  +  ws/01001000/json/ (rota)
    // https://viacep.com.br/ws/01001000/json/
    /* Base URL: https://viacep.com.br/ +
                                        postagens
                                        salvarpostagem
                                        saldo
                                        transferencias
                                        extrato
    */
    // GET , POST , PUT, PATCH, DELETE
    // https://api.banco.inter.com.br/ + saldo ( Ex.: POST => Novo Saldo)
    //@GET("ws/01001000/json/")
    @GET("ws/05028000/json/")
    suspend fun recuperarEndereco() : Response<Endereco>
}