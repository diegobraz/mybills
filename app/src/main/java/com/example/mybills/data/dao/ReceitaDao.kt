package com.example.mybills.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.mybills.domain.Despesa
import com.example.mybills.domain.Receita

@Dao
interface ReceitaDao {

    @Insert
    suspend fun insert(receita: Receita)

    @Query("SELECT * FROM Receita")
    fun getAll(): LiveData<List<Receita>>

    @Update
    suspend fun update(receita: Receita)

    @Query("DELETE FROM Receita WHERE id LIKE :id_" )
    fun delete(id_:Int)

}