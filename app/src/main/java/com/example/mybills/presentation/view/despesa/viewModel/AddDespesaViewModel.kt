package com.example.mybills.presentation.view.despesa.viewModel

import androidx.lifecycle.ViewModel
import com.example.mybills.domain.Despesa
import com.example.mybills.repositorie.DespesaRepositorie

class AddDespesaViewModel( private val  despesaRepositorie: DespesaRepositorie): ViewModel() {

    fun insert(despesa:Despesa){
       despesaRepositorie.insert(despesa)
    }
}