package com.example.mybills.domain

import java.math.BigDecimal
import java.util.*

data class Despesa(
    val valor: BigDecimal,
    val decricao: String,
    val data: Date,
    val pago: Boolean
)
