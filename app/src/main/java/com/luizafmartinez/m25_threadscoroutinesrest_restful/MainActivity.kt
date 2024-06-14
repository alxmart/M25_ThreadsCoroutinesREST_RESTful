package com.luizafmartinez.m25_threadscoroutinesrest_restful

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.luizafmartinez.m25_threadscoroutinesrest_restful.api.EnderecoAPI
import com.luizafmartinez.m25_threadscoroutinesrest_restful.api.RetrofitHelper
import com.luizafmartinez.m25_threadscoroutinesrest_restful.api.model.Endereco
import com.luizafmartinez.m25_threadscoroutinesrest_restful.databinding.ActivityMainBinding
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

    private var pararThread = false

    private var job: Job? = null

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
    }

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
                recuperarEndereco()
            }

            //CoroutineScope(Dispatchers.Main).launch {
            //MainScope().launch {
            //CoroutineScope(Dispatchers.IO).launch {
            //GlobalScope.launch {
            //lifecycleScope.launch {
            /*runBlocking {
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
        }
    }

    private suspend fun recuperarEndereco() {

        var retorno: Response<Endereco>? = null

        try {
            val enderecoAPI = retrofit.create(EnderecoAPI::class.java)
            retorno = enderecoAPI.recuperarEndereco()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_endereco", "Erro ao recuperar")
        }

        if ( retorno != null ) {

            if ( retorno.isSuccessful ) {
                val endereco = retorno.body()
                val rua = endereco?.logradouro
                val cidade = endereco?.localidade
                Log.i("info_endereco", "Endereço: $rua, $cidade")
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