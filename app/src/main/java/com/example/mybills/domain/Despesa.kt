package com.example.mybills.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity
data class Despesa(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val valor: Double,
    val decricao: String,
    val data: String,
    val pago: Boolean
) : Serializable
