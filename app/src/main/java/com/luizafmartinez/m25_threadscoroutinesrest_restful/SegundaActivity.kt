package com.luizafmartinez.m25_threadscoroutinesrest_restful

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.luizafmartinez.m25_threadscoroutinesrest_restful.databinding.ActivitySegundaBinding

class SegundaActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivitySegundaBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnFechar.setOnClickListener {
            finish()
        }

    }
}