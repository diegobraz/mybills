package com.example.mybills.presentation.view.despesa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.mybills.DataAplication
import com.example.mybills.databinding.ActivityDespesasListaBinding
import com.example.mybills.presentation.view.despesa.adapter.DespesaAdapter
import com.example.mybills.presentation.view.despesa.viewModel.AddDespesaViewModel
import com.example.mybills.presentation.view.despesa.viewModel.DespesaViewModelFactory

class DespesasListaActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProvider(this, DespesaViewModelFactory((application as DataAplication).despesaRepositori))
            .get(AddDespesaViewModel::class.java)
    }

    private val biding by lazy { ActivityDespesasListaBinding.inflate(layoutInflater) }

    private val adapter by lazy {
        DespesaAdapter(
            onClickDelete ={id ->
                viewModel.delete(id)
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(biding.root)
        biding.etValorDespesa.adapter = adapter

        viewModel.getAll().observe(this,{despesaList ->
            Log.d("despesa","${despesaList}")
            adapter.submitList(despesaList)
        })
    }
}