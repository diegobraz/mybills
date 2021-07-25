package com.example.mybills.repositorie

import com.example.mybills.data.dao.ReceitaDao
import com.example.mybills.domain.Despesa
import com.example.mybills.domain.Receita
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ReceitaRepositorie(private val receitaDao: ReceitaDao) {
    fun insert(receita: Receita)= runBlocking {
        launch(Dispatchers.IO){
            receitaDao.insert(receita)
        }
    }

    fun update(receita: Receita)= runBlocking {
        launch(Dispatchers.IO){
             receitaDao.update(receita)
        }
    }

    fun getAll() = receitaDao.getAll()


    fun delete(id:Int){
        receitaDao.delete(id)
    }


}