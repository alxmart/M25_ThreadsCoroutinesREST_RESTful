package com.luizafmartinez.m25_threadscoroutinesrest_restful

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.luizafmartinez.m25_threadscoroutinesrest_restful.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private var stopThread = false

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(binding.root)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        binding.BtnAbrir.setOnClickListener {
            startActivity(
                Intent(this, SegundaActivity::class.java)
            )
        }

        binding.btnIniciar.setOnClickListener {

            CoroutineScope(Dispatchers.IO).launch {
                repeat(15) { indice ->
                    Log.i("info_coroutine", "Executando: $indice T: ${Thread.currentThread().name}")

                    withContext(Dispatchers.Main) {
                        //Muda o contexto de execução p/thread principal
                        binding.btnIniciar.text = "Executou"
                    }
                    delay(1000)  // 1000 ms => 1 segundo | UI Thread
                    //Thread.sleep(1000)  // 1000 ms => 1 segundo | UI Thread
                }

                //CoroutineScope( Dispatchers.Main).launch {
                //binding.btnIniciar.text = "Executou"
            }

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

            stopThread = true
            binding.btnIniciar.text = "Reiniciar Execução"
            binding.btnIniciar.isEnabled = true // Habilita o botão novamente
        }

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