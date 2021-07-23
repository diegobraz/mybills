package com.example.mybills.domain

import java.math.BigDecimal
import java.util.*

data class Receita(
    val valor: BigDecimal,
    val decricao: String,
    val data: Date,
    val Recebido: Boolean
)
