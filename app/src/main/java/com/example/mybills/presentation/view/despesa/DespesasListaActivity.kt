package com.example.mybills.presentation.view.despesa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mybills.databinding.ActivityDespesasListaBinding
import com.example.mybills.presentation.adapter.DespesaAdapter

class DespesasListaActivity : AppCompatActivity() {

    private val biding by lazy { ActivityDespesasListaBinding.inflate(layoutInflater) }

    private val adapter by lazy {
        DespesaAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(biding.root)




    }
}