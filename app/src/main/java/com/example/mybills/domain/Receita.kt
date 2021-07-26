package com.example.mybills.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Receita(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val valor: Double,
    val decricao: String,
    val data: String,
    val Recebido: Boolean
) : Serializable
