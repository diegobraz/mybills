package com.example.mybills.presentation.view.main.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mybills.domain.Despesa
import com.example.mybills.domain.Receita
import com.example.mybills.presentation.view.despesa.viewModel.AddDespesaViewModel
import com.example.mybills.repositorie.DespesaRepositorie
import com.example.mybills.repositorie.ReceitaRepositorie

class MainViewModel(
    private val receitaRepositorie: ReceitaRepositorie,
    private val  despesaRepositorie: DespesaRepositorie
    ) : ViewModel() {

    var valorReceita: Double? = null
    private set
    var valorDespesa: Double? = null
    private set



    fun postReceita(value:Double? = null){
        this.valorReceita = value
    }

    fun postDespesa(value: Double?){
        this.valorDespesa =value
    }





    fun getSumReceita(): LiveData<Double> {
        return receitaRepositorie.getSum()
    }

    fun getSumDespesa():LiveData<Double>{
        return despesaRepositorie.getSum()
    }


    fun deleteReceita(id:Int){
        receitaRepositorie.delete(id)
    }
    fun deleteDespesa(id:Int){
        despesaRepositorie.delete(id)
    }


    fun getAllReceita(): LiveData<List<Receita>> {
        return receitaRepositorie.getAll()
    }

    fun getAllDespesa(): LiveData<List<Despesa>> {
        return despesaRepositorie.getAll()
    }


}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val despesaRepositorie: DespesaRepositorie, private  val receitaRepositorie: ReceitaRepositorie): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(despesaRepositorie = despesaRepositorie, receitaRepositorie = receitaRepositorie) as T
    }

}
