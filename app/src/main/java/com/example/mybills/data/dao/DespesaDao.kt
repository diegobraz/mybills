package com.example.mybills.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mybills.domain.Despesa

@Dao
interface DespesaDao {

    @Insert
    suspend fun insert(despesa: Despesa)

    @Query("SELECT * FROM Despesa")
    fun getAll(): LiveData<List<Despesa>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(despesa: Despesa)

    @Query("DELETE FROM Despesa WHERE id LIKE :id_")
    fun delete(id_: Int)

    @Query("SELECT SUM(valor)  FROM Despesa")
    fun getSum(): LiveData<Double>
}