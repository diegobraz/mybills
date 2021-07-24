package com.example.mybills.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.mybills.domain.Despesa

@Dao
interface DespesaDao {

@Insert
suspend fun insert(despesa: Despesa)

@Query("SELECT * FROM Despesa")
fun getAll():LiveData<List<Despesa>>

@Update
suspend fun update(despesa: Despesa)

@Query("DELETE FROM Despesa WHERE id LIKE :id_" )
fun delete(id_:Int)

}