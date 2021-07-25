package com.example.mybills.ultis


import java.text.DecimalFormat

object FormatesTypes {

    val dateFormat = "dd/MM/yyyy"


    val moneyFormatter by lazy {
        DecimalFormat("R$ 0.00")
    }

    val decimalFormatter by lazy {
        DecimalFormat("###,###,###,##0.00")
    }
    val gramaFormatter by lazy {
        DecimalFormat("###,###,###,##0.000")
    }

    val integerFormatter by lazy {
        DecimalFormat("###,###,###,##0")

    }



    fun format(value : Int?) : String {
        return value?.let { integerFormatter.format(it) } ?: ""
    }


    fun formatMoney(value : Double?) : String {
        return if(value != null) moneyFormatter.format(value) else ""
    }
}