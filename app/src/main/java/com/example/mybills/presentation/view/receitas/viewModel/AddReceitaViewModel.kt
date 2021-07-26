package com.example.mybills.presentation.view.receitas.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mybills.domain.Despesa
import com.example.mybills.domain.Receita
import com.example.mybills.presentation.view.despesa.viewModel.AddDespesaViewModel
import com.example.mybills.repositorie.DespesaRepositorie
import com.example.mybills.repositorie.ReceitaRepositorie

class AddReceitaViewMode(private val receitaRepositorie: ReceitaRepositorie):ViewModel() {

    fun insert(receita: Receita){
        receitaRepositorie.insert(receita)
    }

    fun getAll(): LiveData<List<Receita>> {
        return receitaRepositorie.getAll()
    }

    fun delete(id:Int){
        receitaRepositorie.delete(id)
    }

}

@Suppress("UNCHECKED_CAST")
class ReceitaViewModelFactory(private val receitaRepositorie: ReceitaRepositorie): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AddReceitaViewMode(receitaRepositorie = receitaRepositorie) as T
    }
}