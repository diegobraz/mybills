package com.example.mybills.presentation.view.di

import androidx.lifecycle.ViewModel
import com.example.mybills.di.ViewModelKey
import com.example.mybills.presentation.view.despesa.viewModel.AddDespesaViewModel
import com.example.mybills.presentation.view.main.viewModel.MainViewModel
import com.example.mybills.presentation.view.receitas.viewModel.AddReceitaViewMode
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface  MainModulo {

    @Binds
    @IntoMap
    @ViewModelKey(AddDespesaViewModel::class)
    fun bindDespesaViewModel(viewModel: AddDespesaViewModel):ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddReceitaViewMode::class)
    fun bindReceitaViewModel(viewModel: AddReceitaViewMode):ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMain(viewModel: MainViewModel):ViewModel






}