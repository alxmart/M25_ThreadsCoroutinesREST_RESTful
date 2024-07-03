package com.luizafmartinez.m25_threadscoroutinesrest_restful.api

import com.luizafmartinez.m25_threadscoroutinesrest_restful.api.model.Comentario
import com.luizafmartinez.m25_threadscoroutinesrest_restful.api.model.Postagem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface PostagemAPI {
    //https://jsonplaceholder.typicode.com/ + posts
    @GET("posts")
    suspend fun recuperarPostagens() : Response<List<Postagem>>

    @GET("posts/{id}")
    suspend fun recuperarPostagemUnica(
        @Path("id")  id: Int
    ) : Response<Postagem>

    @GET("posts/{id}/comments")
    suspend fun recuperarComentariosParaPostagem(
        @Path("id")  id: Int
    ) : Response<List<Comentario>>

    @GET("comments")   //comments?postId=1&idcomentario=2&
    suspend fun recuperarComentariosParaPostagemQuery(
        @Query("postId")  id: Int
    ) : Response<List<Comentario>>

    /*
    @GET("pesquisa/{marca}/{modelo}") //Path
    suspend fun pesquisaPath(
        @Path("marca")  marca: String, //Com PATH, eu PRECISO ter marca
        @Path("modelo") modelos: String //Com PATH, eu PRECISO ter modelo
    ) : Response<List<Comentario>>

    @GET("pesquisa") //Query  url:  pesquisa?marca=&modelo=civic
    suspend fun pesquisaQuery(
        @Query("marca")  marca: String, //Com QUERY, eu posso passar a marca ou não
        @Query("modelo") modelos: String
    ) : Response<List<Comentario>>
    */

    @POST("posts")
    suspend fun salvarPostagem(
        @Body postagem: Postagem
    ): Response<Postagem>

    @FormUrlEncoded
    @POST("posts")
    suspend fun salvarPostagemFormulario(
        @Field("userId") userId: Int,
        @Field("id") id: Int,
        @Field("title") title: String,
        @Field("body") body: String
    ): Response<Postagem>


    @PUT("posts/{id}")   // Atualização completa
    suspend fun atualizarPostagemPut(
        @Path("id") id: Int,
        @Body postagem: Postagem
    ): Response<Postagem>


}