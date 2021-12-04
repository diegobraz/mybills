package com.example.mybills

import android.app.Application
import com.example.mybills.data.db.AppDataBase
import com.example.mybills.di.ApplicationComponent
import com.example.mybills.di.DaggerApplicationComponent
import com.example.mybills.repositorie.DespesaRepositorie
import com.example.mybills.repositorie.ReceitaRepositorie


class DataAplication: Application(){
    val dataBase by lazy { AppDataBase.getDatabase(this) }
    val receitaRepositori  by lazy { ReceitaRepositorie(dataBase.recietaDao()) }
    val despesaRepositori by lazy { DespesaRepositorie(dataBase.despesaDao()) }
    val appComponent by lazy { DaggerApplicationComponent.factory().create(this) }
}