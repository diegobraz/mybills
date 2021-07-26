package com.example.mybills.presentation.view.receitas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.mybills.DataAplication
import com.example.mybills.databinding.ActivityReceitasListaBinding
import com.example.mybills.domain.Receita
import com.example.mybills.presentation.view.receitas.adapter.ReceitasAdapter
import com.example.mybills.presentation.view.receitas.viewModel.AddReceitaViewMode
import com.example.mybills.presentation.view.receitas.viewModel.ReceitaViewModelFactory

class ReceitasListaActivity : AppCompatActivity() {

    private val biding by lazy { ActivityReceitasListaBinding.inflate(layoutInflater) }

    private val adapter by lazy {
        ReceitasAdapter(
            onClickDelete ={id ->
                viewModel.delete(id)
            },
            onclickUpdadte = {
                onCreateUpdate(it)
            }

        )
    }

    private fun onCreateUpdate(receita: Receita) {

        startActivity(
            Intent(
                this,
                EditReceitaActivity::class.java
            ).apply {
                putExtra("receita",receita)
            }
        )


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