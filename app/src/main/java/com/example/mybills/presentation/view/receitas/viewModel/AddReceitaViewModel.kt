package com.example.mybills.presentation.view.receitas.viewModel

import androidx.lifecycle.ViewModel
import com.example.mybills.domain.Receita
import com.example.mybills.repositorie.ReceitaRepositorie

class AddReceitaViewMode(private val receitaRepositorie: ReceitaRepositorie):ViewModel() {

    fun insert(receita: Receita){
        receitaRepositorie.insert(receita)
    }


}