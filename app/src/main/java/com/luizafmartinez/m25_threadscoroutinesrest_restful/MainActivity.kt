package com.luizafmartinez.m25_threadscoroutinesrest_restful

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.luizafmartinez.m25_threadscoroutinesrest_restful.api.EnderecoAPI
import com.luizafmartinez.m25_threadscoroutinesrest_restful.api.FilmeAPI
import com.luizafmartinez.m25_threadscoroutinesrest_restful.api.PostagemAPI
import com.luizafmartinez.m25_threadscoroutinesrest_restful.api.RetrofitHelper
import com.luizafmartinez.m25_threadscoroutinesrest_restful.api.model.Comentario
import com.luizafmartinez.m25_threadscoroutinesrest_restful.api.model.Endereco
import com.luizafmartinez.m25_threadscoroutinesrest_restful.api.model.FilmeDetalhes
import com.luizafmartinez.m25_threadscoroutinesrest_restful.api.model.FilmeResposta
import com.luizafmartinez.m25_threadscoroutinesrest_restful.api.model.Foto
import com.luizafmartinez.m25_threadscoroutinesrest_restful.api.model.Postagem
import com.luizafmartinez.m25_threadscoroutinesrest_restful.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val retrofit by lazy {
        RetrofitHelper.retrofit
    }

    private val filmeAPI by lazy {
        RetrofitHelper.filmeAPI
    }

    /*private val apiViaCEP by lazy {
        RetrofitHelper.apiViaCEP
    }*/

    private var pararThread = false

    private var job: Job? = null

    /*override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnAbrir.setOnClickListener {
            startActivity(
                Intent(this, SegundaActivity::class.java)
            )
            finish()
        }

        binding.btnParar.setOnClickListener {
            //pararThread = true
            job?.cancel()
            binding.btnIniciar.text = "Reiniciar Execução"
            binding.btnIniciar.isEnabled = true
        }

        binding.btnIniciar.setOnClickListener {

            CoroutineScope(Dispatchers.IO).launch {
                //recuperarEndereco()
                //recuperarPostagens()
                //recuperarPostagemUnica()
                //recuperarComentariosParaPostagem()
                //salvarPostagem()
                //atualizarPostagem()
                //removerPostagem()
                //recuperarFotoUnica()

                //--------------------------------
                // API The Movie DB
                //--------------------------------
                //recuperarFilmesPopulares()
                //recuperarDetalhesFilme()
                recuperarFilmePesquisa()

            }

            /*
            //-------------------------------------------------
            // Tipos de escopo de Coroutines:
            //------------------------------------------------
            //CoroutineScope(Dispatchers.Main).launch {
            //MainScope().launch {
            //CoroutineScope(Dispatchers.IO).launch {
            //GlobalScope.launch {
            //lifecycleScope.launch {
            /*runBlocking {    //Bloqueia a thread principal/Não usar p/ execução longa
                binding.btnIniciar.text = "Executando"
                *//*repeat(15) { indice ->
                    //binding.btnIniciar.text = "Executando: $indice"
                    Log.i("info_coroutine", "Executando: $indice T: ${Thread.currentThread().name}")
                    delay(1000L)
                }*//*
            }*/

            //CoroutineScope( Dispatchers.Main ).launch {

            /*job = CoroutineScope(Dispatchers.IO).launch {

                *//*withTimeout(7000L) {
                    executar()
                }*//*

                val tempo = measureTimeMillis {

                    *//*var resultado1: String? = null
                    var resultado2: String? = null*//*

                    //val job1 = launch {
                    val resultado1 = async {
                        tarefa1()
                    }

                    //val job2 = launch {
                    val resultado2 = async {
                        tarefa2()
                    }

                    *//*job1.join()
                    job2.join() // Só avança quando os dois terminarem
                    *//*

                    Log.i("info_coroutine", "Resultado1: ${resultado1.await()}")
                    Log.i("info_coroutine", "Resultado2: ${resultado2.await()}")

                    //Mostrar na interface
                    withContext(Dispatchers.Main) {
                        binding.btnIniciar.text = "${resultado1.await()}"
                        binding.btnParar.text = "${resultado2.await()}"
                    }

                }

                Log.i("info_coroutine", "Tempo: $tempo")

                //recuperarUsuarioLogado()

                //binding.btnIniciar.text = "Executou"
                *//*repeat(15) { indice ->
                    Log.i("info_coroutine", "Executando: $indice T: ${Thread.currentThread().name}")

                    withContext(Dispatchers.Main) {
                        binding.btnIniciar.text = "Executando: $indice T: ${Thread.currentThread().name}"
                    }
                    //Thread.sleep(1000)
                    delay(1000)
                }*//*
            }*/

            //Thread(MinhaRunnable()).start()

            /*Thread {
                repeat(30) { indice ->
                    Log.i("info_thread", "Minha Thread: $indice T: ${Thread.currentThread().name}")
                    runOnUiThread { // Usar só para atualizações de Interface
                        binding.btnIniciar.text = "Executando: $indice T: ${Thread.currentThread().name}"
                        binding.btnIniciar.isEnabled = false
                        if (indice == 29) {
                            binding.btnIniciar.text = "Reiniciar Execução"
                            binding.btnIniciar.isEnabled = true
                        }
                    }
                    Thread.sleep(1000)
                }
            } .start()*/

            // Thread( MinhaRunnable() ).start()

            // MinhaThread().start()

            /*repeat(15) { indice->
                Log.i("info_thread", "Executando: $indice T: ${Thread.currentThread().name}")
                Thread.sleep(1000)
            }*/

            //MinhaThread().start()

            /*val minhaThread = MinhaThread()
            minhaThread.start()*/

            /*repeat(30) { indice->
                Log.i("info_thread", "Executando: $indice")
                Thread.sleep(1000)
            }*/
            */
        }
    }

    private suspend fun recuperarFilmePesquisa() {

        var retorno: Response<FilmeResposta>? = null

        try {
            retorno = filmeAPI.recuperarFilmePesquisa("hocus")
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_jsonplace", "Erro ao recuperar filmes pesquisa.")
        }

        if (retorno != null) {

            if (retorno.isSuccessful) {

                val filmeResposta = retorno.body() // Pega o corpo da Response
                val listaFilmes = filmeResposta?.results

                Log.i("info_tmdb", "CÓDIGO: ${retorno.code()}")

                listaFilmes?.forEach { filme ->
                    val id = filme.id
                    val titulo = filme.title
                    Log.i("info_tmdb", "Filme: $id - $titulo")
                }
            } else {
                Log.i("info_tmdb", "Erro, CÓDIGO: ${retorno.code()}")
            }
        }
    }

    private suspend fun recuperarDetalhesFilme() {

        var retorno: Response<FilmeDetalhes>? = null

        try {
            retorno = filmeAPI.recuperarDetalhesFilme(436270)
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_tmdb", "Erro ao recuperar detalhes filme.")
        }

        if (retorno != null) {

            if (retorno.isSuccessful) {

                val filmeDetalhes = retorno.body() // Pega o corpo da Response

                val titulo = filmeDetalhes?.title
                val listaGeneros = filmeDetalhes?.genres
                val pais = filmeDetalhes?.production_countries?.get(0)

                // https://image.tmdb.org/t/p/ + w500 + /1E5baAaEse26fej7uHcjOgEE2t2.jpg
                val nomeImagem = filmeDetalhes?.backdrop_path

                val url = RetrofitHelper.BASE_URL_IMAGE + "w1280" + nomeImagem

                withContext(Dispatchers.Main) {
                    Picasso.get()
                        .load( url )
                        .into( binding.imageFoto )
                }

                Log.i("info_tmdb", "CÓDIGO: ${retorno.code()}")
                Log.i("info_tmdb", "Título: $titulo")
                Log.i("info_tmdb", "País: ${pais?.name}")

                listaGeneros?.forEach { genero ->
                    Log.i("info_tmdb", "Gênero: $genero")
                }
            } else {
                Log.i("info_tmdb", "Erro, CÓDIGO: ${retorno.code()}")
            }
        }
    }

    private suspend fun recuperarFilmesPopulares() {

        var retorno: Response<FilmeResposta>? = null

        try {
            retorno = filmeAPI.recuperarFilmesPopulares()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_jsonplace", "Erro ao recuperar filmes populares.")
        }

        if (retorno != null) {

            if (retorno.isSuccessful) {

                val filmeResposta = retorno.body() // Pega o corpo da Response
                val listaFilmes = filmeResposta?.results

                val pagina = filmeResposta?.page
                val totalPaginas = filmeResposta?.total_pages
                val totalDeFilmes = filmeResposta?.total_results

                Log.i("info_tmdb", "CÓDIGO: ${retorno.code()}")
                Log.i("info_tmdb", "Página: $pagina")
                Log.i("info_tmdb", "Total de Páginas: $totalPaginas")
                Log.i("info_tmdb", "Toal de Filmes: $totalDeFilmes")

                listaFilmes?.forEach { filme ->
                    val id = filme.id
                    val titulo = filme.title
                    Log.i("info_tmdb", "Filme: $id - $titulo")
                }
            } else {
                Log.i("info_tmdb", "Erro, CÓDIGO: ${retorno.code()}")
            }
        }
    }

    private suspend fun recuperarFotoUnica() {

        var retorno: Response<Foto>? = null

        try {

            val postagemAPI = retrofit.create(PostagemAPI::class.java)

            retorno = postagemAPI.recuperarFoto(5)

        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_jsonplace", "Erro ao recuperar foto.")
        }

        if (retorno != null) {

            if (retorno.isSuccessful) {

                val foto = retorno.body() // Pega o corpo da Response

                val resultado = "[${retorno.code()}] - ${foto?.id} - ${foto?.url}"

                withContext(Dispatchers.Main) {
                    binding.textResultado.text = resultado
                    Picasso.get()
                           //.load(foto?.url)
                           .load( R.drawable.picasso)
                           .resize(100, 100)
                           //.centerInside()
                           //.centerCrop()
                           .placeholder( R.drawable.carregando )
                           //.error( R.drawable.erro )
                           .into(binding.imageFoto)
                }

                Log.i("info_jsonplace", resultado)

            } else {
                withContext(Dispatchers.Main) {
                    binding.textResultado.text = "ERRO CODE: ${retorno.code()}"
                }
            }
        }
    }

    private suspend fun removerPostagem() {

        var retorno: Response<Unit>? = null

        try {
            val postagemAPI = retrofit.create(PostagemAPI::class.java)
            retorno = postagemAPI.removerPostagem(1)
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_jsonplace", "Erro ao deletar.")
        }

        if (retorno != null) {
            if (retorno.isSuccessful) {
                var resultado = "[${retorno.code()}] Sucesso ao remover postagem"

                withContext(Dispatchers.Main) {
                    binding.textResultado.text = resultado
                }

            } else {
                withContext(Dispatchers.Main) {
                    binding.textResultado.text = "ERRO CODE: ${retorno.code()}"
                }
            }
        }
    }

    private suspend fun atualizarPostagem() {

        var retorno: Response<Postagem>? = null

        try {
            val postagemAPI = retrofit.create(PostagemAPI::class.java)
            retorno = postagemAPI.atualizarPostagemPatch(
                1,
                Postagem(
                    "Corpo da postagem",
                    -1,
                    //"Título",
                    null, //PATCH - null, não altera o título !!
                    1090
                )
            )

            /*
            retorno = postagemAPI.atualizarPostagemPut(
                1,
                Postagem(
                    "Corpo da postagem",
                    -1,
                    //"Título",
                    null, //Atualiza todos os valores, mesmo se nulo
                    1090
                )
            )
            */
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_jsonplace", "Erro ao recuperar.")
        }

        if (retorno != null) {
            if (retorno.isSuccessful) {
                val postagem = retorno.body()

                val id = postagem?.id
                val titulo = postagem?.title
                val idUsuario = postagem?.userId
                val corpo = postagem?.body

                var resultado = "[${retorno.code()}] ID:$id - T:$titulo C:$corpo - U:$idUsuario"

                withContext(Dispatchers.Main) {
                    binding.textResultado.text = resultado
                }

            } else {
                withContext(Dispatchers.Main) {
                    binding.textResultado.text = "ERRO CODE: ${retorno.code()}"
                }
            }
        }
    }

    private suspend fun salvarPostagem() {

        var retorno: Response<Postagem>? = null

        /*
        val postagem = Postagem(
            "Corpo da postagem",
            -1,
            "Título da postagem",
            1090
        )
        */

        try {
            val postagemAPI = retrofit.create(PostagemAPI::class.java)
            //retorno = postagemAPI.salvarPostagem(postagem)
            retorno = postagemAPI.salvarPostagemFormulario(
                1090,
                -1,
                "Título da postagem Formulário",
                "Corpo da postagem"
            )

        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_jsonplace", "Erro ao recuperar.")
        }

        if (retorno != null) {

            if (retorno.isSuccessful) {
                val postagem = retorno.body()

                val id = postagem?.id
                val titulo = postagem?.title
                val idUsuario = postagem?.userId
                var resultado = "[${retorno.code()}]id:$id - T:$titulo - U:$idUsuario"

                withContext(Dispatchers.Main) {
                    binding.textResultado.text = resultado
                }
            } else {
                withContext(Dispatchers.Main) {
                    binding.textResultado.text = "ERRO CODE: ${retorno.code()}"
                }
            }
        }
    }

    private suspend fun recuperarComentariosParaPostagem() {

        var retorno: Response<List<Comentario>>? = null

        try {// Passa a Interface e cria objeto (enderecoAPI)
            val postagemAPI = retrofit.create(PostagemAPI::class.java)

            // Path
            //retorno = postagemAPI.recuperarComentariosParaPostagem(1)

            // Query
            retorno = postagemAPI.recuperarComentariosParaPostagemQuery(1)

        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_jsonplace", "Erro ao recuperar.")
        }

        if (retorno != null) {

            if (retorno.isSuccessful) {
                val listaComentarios = retorno.body() // Pega o corpo da Response

                var resultado = ""
                listaComentarios?.forEach { comentario ->
                    val idComentario = comentario.id
                    val email = comentario.email
                    val comentarioResultado = "$idComentario - $email \n"
                    resultado += comentarioResultado
                }

                withContext(Dispatchers.Main) {
                    binding.textResultado.text = resultado
                }

            }
        }
    }

    private suspend fun recuperarPostagemUnica() {

        var retorno: Response<Postagem>? = null

        try {// Passa a Interface e cria objeto (enderecoAPI)
            val postagemAPI = retrofit.create(PostagemAPI::class.java)
            retorno = postagemAPI.recuperarPostagemUnica(2) //Método dentro da Interface PostagemAPI
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_jsonplace", "Erro ao recuperar.")
        }

        if (retorno != null) {

            if (retorno.isSuccessful) {
                val postagem = retorno.body() // Pega o corpo da Response
                val resultado = "${postagem?.id} - ${postagem?.title}"

                withContext(Dispatchers.Main) {
                    binding.textResultado.text = resultado
                }

                Log.i("info_jsonplace", resultado)

            } else {
                Log.i("info_jsonplace", "")
            }
        }
    }

    private suspend fun recuperarPostagens() {

        var retorno: Response<List<Postagem>>? = null

        try {// Passa a Interface e cria objeto (enderecoAPI)
            val postagemAPI = retrofit.create(PostagemAPI::class.java)
            retorno = postagemAPI.recuperarPostagens() //Método dentro da Interface PostagemAPI
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_jsonplace", "Erro ao recuperar.")
        }

        if (retorno != null) {

            if (retorno.isSuccessful) {
                val listapostagens = retorno.body() // Pega o corpo da Response
                listapostagens?.forEach { postagem ->
                    val id = postagem.id
                    val titulo = postagem.title
                    Log.i("info_jsonplace", "Postagem: $id - $titulo")
                }
            } else {
                Log.i("info_jsonplace", "")
            }
        }
    }

    private suspend fun recuperarEndereco() {

        var retorno: Response<Endereco>? = null
        val cepDigitadoUsuario = "05028000"   //binding.editNome.text

        try {// Passa a Interface e cria objeto (enderecoAPI)
            val enderecoAPI = retrofit.create(EnderecoAPI::class.java)
            retorno =
                enderecoAPI.recuperarEndereco(cepDigitadoUsuario) //Método dentro da Interface EnderecoAPI
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_endereco", "Erro ao recuperar.")
        }

        if (retorno != null) {
            if (retorno.isSuccessful) {
                val endereco = retorno.body() // Pega o corpo da Response
                val rua = endereco?.logradouro
                val cidade = endereco?.localidade
                val cep = endereco?.cep
                Log.i("info_endereco", "Endereço: $rua, $cidade, t: $cep")
            }
        }
    }

    /*override fun onStop() {
        super.onStop()
        job?.cancel()
    }*/

    private suspend fun tarefa1(): String {
        repeat(3) { indice ->
            Log.i("info_coroutine", "Tarefa1: $indice T: ${Thread.currentThread().name}")
            delay(1000L)  // 1000 ms => 1 segundo | UI Thread
        }
        return "Executou Tarefa 1"
    }

    private suspend fun tarefa2(): String {
        repeat(3) { indice ->
            Log.i("info_coroutine", "Tarefa2: $indice T: ${Thread.currentThread().name}")
            delay(1000L)  // 1000 ms => 1 segundo | UI Thread
        }
        return "Executou Tarefa 2"
    }

    private suspend fun executar() {
        repeat(15) { indice ->
            Log.i("info_coroutine", "Executando: $indice T: ${Thread.currentThread().name}")

            withContext(Dispatchers.Main) {
                binding.btnIniciar.text =
                    "Executando: $indice T: ${Thread.currentThread().name}"
                binding.btnIniciar.isEnabled = false
            }
            delay(1000L)
        }
    }

    private suspend fun dadosUsuario() {
        //private suspend fun executar () {

        val usuario = recuperarUsuarioLogado()
        Log.i("info_coroutine", "Usuario: ${usuario.nome} T: ${Thread.currentThread().name}")

        val postagens = recuperarPostagemPeloId(usuario.id)
        Log.i(
            "info_coroutine",
            "Postagens: ${postagens.size} postagens T: ${Thread.currentThread().name}"
        )
    }

    private suspend fun recuperarPostagemPeloId(idUsuario: Int): List<String> {
        delay(2000) // 2 segundos
        return listOf(
            "Viagem para o Nordeste",
            "Estudando Android",
            "jantando restaurante"
        )
    }

    private suspend fun recuperarUsuarioLogado(): Usuario {
        delay(2000) // 2 segundos
        return Usuario(1020, "Jamilton Damasceno")
    }

    inner class MinhaRunnable : Runnable {
        override fun run() {
            repeat(30) { indice ->
                if (pararThread) {
                    pararThread = false
                    return
                }
                Log.i("info_thread", "Minha Thread: $indice T: ${Thread.currentThread().name}")
                runOnUiThread { // Usar só para atualizações de Interface
                    binding.btnIniciar.text =
                        "Executando: $indice T: ${Thread.currentThread().name}"
                    binding.btnIniciar.isEnabled = false
                    if (indice == 29) {
                        binding.btnIniciar.text = "Reiniciar Execução"
                        binding.btnIniciar.isEnabled = true
                    }
                }
                Thread.sleep(1000)
            }
        }
    }

    inner class MinhaThread : Thread() {
        override fun run() {
            super.run()
            repeat(30) { indice ->
                Log.i("info_thread", "Minha Thread: $indice T: ${currentThread().name}")
                runOnUiThread { // Usar só para atualizações de Interface
                    binding.btnIniciar.text = "Executando: $indice T: ${currentThread().name}"
                    binding.btnIniciar.isEnabled = false
                    if (indice == 29) {
                        binding.btnIniciar.text = "Reiniciar Execução"
                        binding.btnIniciar.isEnabled = true
                    }
                }
                sleep(1000)
            }
        }
    }
}