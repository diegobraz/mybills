package com.example.mybills.presentation.view.main.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mybills.domain.Receita
import com.example.mybills.presentation.view.despesa.viewModel.AddDespesaViewModel
import com.example.mybills.repositorie.DespesaRepositorie
import com.example.mybills.repositorie.ReceitaRepositorie

class MainViewModel(
    private val receitaRepositorie: ReceitaRepositorie,
    private val  despesaRepositorie: DespesaRepositorie
    ) : ViewModel() {


}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val despesaRepositorie: DespesaRepositorie, private  val receitaRepositorie: ReceitaRepositorie): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(despesaRepositorie = despesaRepositorie, receitaRepositorie = receitaRepositorie) as T
    }

}
