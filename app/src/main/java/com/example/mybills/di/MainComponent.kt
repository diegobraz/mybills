package com.example.mybills.di

import com.example.mybills.presentation.view.despesa.AddDespesasActivity
import com.example.mybills.presentation.view.despesa.DespesasListaActivity
import com.example.mybills.presentation.view.despesa.EditDespesaActivity
import com.example.mybills.presentation.view.di.MainModulo
import com.example.mybills.presentation.view.main.MainActivity
import com.example.mybills.presentation.view.receitas.AddReceitaActivity
import com.example.mybills.presentation.view.receitas.EditReceitaActivity
import com.example.mybills.presentation.view.receitas.ReceitasListaActivity
import dagger.Subcomponent

@Subcomponent(modules = [MainModulo::class])
interface MainComponent {

    @Subcomponent.Factory
    interface factory{
        fun create():MainComponent
    }

    fun inject(activity: AddDespesasActivity)
    fun inject(activity: DespesasListaActivity)
    fun inject(activity: EditDespesaActivity)
    fun inject(activity: AddReceitaActivity)
    fun inject(activity: EditReceitaActivity)
    fun inject(activity: ReceitasListaActivity)
    fun inject(activity: MainActivity)

}