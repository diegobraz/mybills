package com.example.mybills.repositorie

import com.example.mybills.data.dao.DespesaDao
import com.example.mybills.domain.Despesa
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class DespesaRepositorie(private val despesaDao: DespesaDao) {

  fun insert(despesa: Despesa)= runBlocking {
        launch(Dispatchers.IO){
            despesaDao.insert(despesa)
        }
  }

    fun update(despesa: Despesa)= runBlocking {
        launch(Dispatchers.IO){
            despesaDao.update(despesa)
        }
    }

    fun getAll() = despesaDao.getAll()


    fun delete(id: Int) = runBlocking {
        launch(Dispatchers.IO) {
            despesaDao.delete(id)
        }
    }

    fun getSum() = despesaDao.getSum()



}