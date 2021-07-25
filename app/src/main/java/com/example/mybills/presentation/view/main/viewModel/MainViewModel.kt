package com.example.mybills.presentation.view.main.viewModel

import androidx.lifecycle.ViewModel
import com.example.mybills.domain.Receita
import com.example.mybills.repositorie.DespesaRepositorie
import com.example.mybills.repositorie.ReceitaRepositorie

class MainViewModel(
    private val receitaRepositorie: ReceitaRepositorie,
    private val  despesaRepositorie: DespesaRepositorie
    ) : ViewModel() {


}