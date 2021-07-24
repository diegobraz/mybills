package com.example.mybills.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mybills.data.dao.DespesaDao
import com.example.mybills.data.dao.ReceitaDao
import com.example.mybills.domain.Despesa
import com.example.mybills.domain.Receita

@Database(entities = [Despesa::class, Receita::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun recietaDao(): ReceitaDao
    abstract fun despesaDao(): DespesaDao

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                val intance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "businesscard_db"
                ).build()
                INSTANCE = intance
                intance
            }
        }
    }
}