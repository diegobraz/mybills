package com.example.mybills.presentation.view.despesa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.mybills.DataAplication
import com.example.mybills.databinding.ActivityDespesasListaBinding
import com.example.mybills.domain.Despesa
import com.example.mybills.presentation.view.despesa.adapter.DespesaAdapter
import com.example.mybills.presentation.view.despesa.viewModel.AddDespesaViewModel
import com.example.mybills.presentation.view.despesa.viewModel.DespesaViewModelFactory
import com.example.mybills.presentation.view.receitas.EditReceitaActivity

class DespesasListaActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            DespesaViewModelFactory((application as DataAplication).despesaRepositori)
        )
            .get(AddDespesaViewModel::class.java)
    }

    private val biding by lazy { ActivityDespesasListaBinding.inflate(layoutInflater) }

    private val adapter by lazy {
        DespesaAdapter(
            onClickDelete = { id ->
                viewModel.delete(id)
            },
            onclickUpdadte = {
                onCreateUpdate(it)
            }

        )
    }

    private fun onCreateUpdate(despesa: Despesa) {
        startActivity(
            Intent(
                this,
                EditReceitaActivity::class.java
            ).apply {
                putExtra("despesa", despesa)
            }
        )


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(biding.root)
        biding.etValorDespesa.adapter = adapter
        viewModel.getAll().observe(this, { despesaList ->
            Log.d("despesa", "${despesaList}")
            adapter.submitList(despesaList)
        })
    }
}