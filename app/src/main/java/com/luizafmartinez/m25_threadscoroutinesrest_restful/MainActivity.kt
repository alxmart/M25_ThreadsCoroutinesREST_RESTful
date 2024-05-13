package com.luizafmartinez.m25_threadscoroutinesrest_restful

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.luizafmartinez.m25_threadscoroutinesrest_restful.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

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

            //MinhaThread().start()

            /*val minhaThread = MinhaThread()
            minhaThread.start()  */

            repeat(30) { indice ->
                Log.i("info_thread", "Executando: $indice T: ${Thread.currentThread().name}")
                Thread.sleep(1000)  // 1000 ms => 1 segundo | UI Thread
            }
        }

    }

    inner class MinhaThread : Thread() {
        override fun run() {
            super.run()

            repeat(30) { indice ->
                Log.i("info_thread", "Executando: $indice T: ${currentThread().name}")
                sleep(1000)  // 1000 ms => 1 segundo | UI Thread
            }
        }
    }

}