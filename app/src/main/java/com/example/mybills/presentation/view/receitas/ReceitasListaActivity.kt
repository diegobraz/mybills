package com.example.mybills.presentation.view.receitas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.mybills.DataAplication
import com.example.mybills.databinding.ActivityReceitasListaBinding
import com.example.mybills.presentation.view.receitas.adapter.ReceitasAdapter
import com.example.mybills.presentation.view.receitas.viewModel.AddReceitaViewMode
import com.example.mybills.presentation.view.receitas.viewModel.ReceitaViewModelFactory

class ReceitasListaActivity : AppCompatActivity() {

    private val biding by lazy { ActivityReceitasListaBinding.inflate(layoutInflater) }

    private val adapter by lazy {
        ReceitasAdapter()
    }

    private val viewModel by lazy {
        ViewModelProvider(this, ReceitaViewModelFactory((application as DataAplication).receitaRepositori))
            .get(AddReceitaViewMode::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(biding.root)
        biding.rvReceittas.adapter = adapter
        viewModel.getAll().observe(this,{ receitaList ->

            adapter.submitList(receitaList)

        })

    }

}