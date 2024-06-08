package com.luizafmartinez.m25_threadscoroutinesrest_restful

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.luizafmartinez.m25_threadscoroutinesrest_restful.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private var stopThread = false

    private var job : Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnAbrir.setOnClickListener {
            startActivity(
                Intent(this, SegundaActivity::class.java)
            )
        }

        binding.btnIniciar.setOnClickListener {

            job = CoroutineScope(Dispatchers.IO).launch {
                /*
                withTimeout(7000L) {
                    executar()
                }
                */
                val resultado1 = tarefa1()
                val resultado2 = tarefa2()
                Log.i("info_coroutine", "Resultado1: $resultado1")
                Log.i("info_coroutine", "Resultado2: $resultado2")

            }
                //recuperarUsuarioLogado()

                /*repeat(15) { indice ->
                    Log.i("info_coroutine", "Executando: $indice T: ${Thread.currentThread().name}")

                    withContext(Dispatchers.Main) {
                        //Muda o contexto de execução p/thread principal
                        binding.btnIniciar.text = "Executou"
                    }

                    delay(1000)  // 1000 ms => 1 segundo | UI Thread
                    //Thread.sleep(1000)  // 1000 ms => 1 segundo | UI Thread
                }*/

                //CoroutineScope( Dispatchers.Main).launch {
                //binding.btnIniciar.text = "Executou"


            //Thread(MinhaRunnable()).start()

            // ou
            // Thread {
            //   repeat(30) { indice ->
            //                Log.i("info_thread", "Minha Thread: $indice T: ${currentThread().name}")
            //                //binding.btnIniciar.text = "Executando"
            //                runOnUiThread { // Só usar para atualizações de interface !!
            //                    binding.btnIniciar.text = "Executando $indice T: ${currentThread().name}"
            //                    binding.btnIniciar.isEnabled = false // Desabilita o button
            //                    if ( indice == 29 ) {
            //                        binding.btnIniciar.text = "Reiniciar Execução"
            //                        binding.btnIniciar.isEnabled = true // Habilita o botão novamente
            //                    }
            //                }
            //                sleep(1000)  // 1000 ms => 1 segundo | UI Thread
            //            }
            // }.start()

            //MinhaThread().start()

            /*val minhaThread = MinhaThread()
            minhaThread.start()  */

            /*repeat(30) { indice ->
                Log.i("info_thread", "Executando: $indice T: ${Thread.currentThread().name}")
                Thread.sleep(1000)  // 1000 ms => 1 segundo | UI Thread
            }*/
        }

        binding.btnParar.setOnClickListener {
            //stopThread = true
            job?.cancel()
            binding.btnIniciar.text = "Reiniciar Execução"
            binding.btnIniciar.isEnabled = true // Habilita o botão novamente
        }
    }

    // Parar a execução se o usuário sair da tela:
    /*override fun onStop() {
        super.onStop()
        job?.cancel()
    }*/

    private suspend fun tarefa1() : String {
        repeat(15) { indice ->
            Log.i("info_coroutine", "Tarefa1: $indice T: ${Thread.currentThread().name}")
            delay(1000L)  // 1000 ms => 1 segundo | UI Thread
        }
        return "Executou Tarefa 1"
    }

    private suspend fun tarefa2() : String {
        repeat(15) { indice ->
            Log.i("info_coroutine", "Tarefa2: $indice T: ${Thread.currentThread().name}")
            delay(1000L)  // 1000 ms => 1 segundo | UI Thread
        }
        return "Executou Tarefa 2"
    }

    private suspend fun executar() {
        repeat(15) { indice ->
            Log.i("info_coroutine", "Executando: $indice T: ${Thread.currentThread().name}")
            withContext(Dispatchers.Main) {
                //Muda o contexto de execução p/thread principal
                binding.btnIniciar.text = "Executando: $indice T: ${Thread.currentThread().name}"
                binding.btnIniciar.isEnabled = false // Desabilita o button
            }
            delay(1000L)  // 1000 ms => 1 segundo | UI Thread
        }
    }

    private suspend fun dadosUsuario() {
        val usuario = recuperarUsuarioLogado()
        Log.i("info_coroutine", "Usuario: ${usuario.nome} T: ${Thread.currentThread().name}")
        val postagens = recuperarPostagensPeloId(usuario.id)
        Log.i("info_coroutine", "Postagens: ${postagens.size} T: ${Thread.currentThread().name}")
    }

    private suspend fun recuperarPostagensPeloId(idUsuario: Int): List<String> {
        delay(2000) // 2 segundos
        return listOf(
            "Viagem para o Nordeste",
            "Estudando Android",
            "Jantando no Restaurante"
        )
    }

    private suspend fun recuperarUsuarioLogado(): Usuario {
        delay(2000) // 2 segundos
        return Usuario(1020, "Jamilton Damasceno")
    }

    inner class MinhaRunnable : Runnable {
        override fun run() {
            repeat(30) { indice ->
                if (stopThread) {
                    stopThread = false
                    return
                }
                Log.i("info_thread", "Minha Thread: $indice T: ${Thread.currentThread().name}")
                //binding.btnIniciar.text = "Executando"
                runOnUiThread { // Só usar para atualizações de interface !!
                    binding.btnIniciar.text = "Executando $indice T: ${Thread.currentThread().name}"
                    binding.btnIniciar.isEnabled = false // Desabilita o button
                    if (indice == 29) {
                        binding.btnIniciar.text = "Reiniciar Execução"
                        binding.btnIniciar.isEnabled = true // Habilita o botão novamente
                    }
                }
                Thread.sleep(1000)  // 1000 ms => 1 segundo | UI Thread
            }
        }
    }

    inner class MinhaThread : Thread() {
        override fun run() {
            super.run()
            repeat(30) { indice ->
                Log.i("info_thread", "Minha Thread: $indice T: ${currentThread().name}")
                //binding.btnIniciar.text = "Executando"
                runOnUiThread { // Só usar para atualizações de interface !!
                    binding.btnIniciar.text = "Executando $indice T: ${currentThread().name}"
                    binding.btnIniciar.isEnabled = false // Desabilita o button
                    if (indice == 29) {
                        binding.btnIniciar.text = "Reiniciar Execução"
                        binding.btnIniciar.isEnabled = true // Habilita o botão novamente
                    }
                }
                sleep(1000)  // 1000 ms => 1 segundo | UI Thread
            }
        }
    }

}
