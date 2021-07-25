package com.example.mybills.presentation.view.receitas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mybills.databinding.ActivityReceitasListaBinding
import com.example.mybills.presentation.view.receitas.adapter.ReceitasAdapter

class ReceitasListaActivity : AppCompatActivity() {

    private val biding by lazy { ActivityReceitasListaBinding.inflate(layoutInflater) }

    private val adapter by lazy {
        ReceitasAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(biding.root)
        getAllReceitas()
    }

    private fun getAllReceitas() {

    }

}