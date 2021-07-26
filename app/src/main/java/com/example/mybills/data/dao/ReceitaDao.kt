package com.example.mybills.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mybills.domain.Receita

@Dao
interface ReceitaDao {

    @Insert
    suspend fun insert(receita: Receita)

    @Query("SELECT * FROM Receita")
    fun getAll(): LiveData<List<Receita>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(receita: Receita)

    @Query("DELETE FROM Receita WHERE id LIKE :id_")
    fun delete(id_: Int)

    @Query("SELECT  SUM(valor)  FROM Receita")
    fun getSum(): LiveData<Double>

}