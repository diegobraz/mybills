package com.example.mybills.presentation.view.despesa.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mybills.domain.Despesa
import com.example.mybills.repositorie.DespesaRepositorie

class AddDespesaViewModel(private val despesaRepositorie: DespesaRepositorie) : ViewModel() {

    fun insert(despesa: Despesa) {
        despesaRepositorie.insert(despesa)
    }

    fun getAll(): LiveData<List<Despesa>> {
        return despesaRepositorie.getAll()
    }

    fun delete(id: Int) {
        despesaRepositorie.delete(id)
    }

    fun update(despesa: Despesa) {
        despesaRepositorie.update(despesa)
    }

}


@Suppress("UNCHECKED_CAST")
class DespesaViewModelFactory(private val despesaRepositorie: DespesaRepositorie) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AddDespesaViewModel(despesaRepositorie = despesaRepositorie) as T
    }


}

