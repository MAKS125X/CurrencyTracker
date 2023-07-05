package com.example.currencytracker.models

import kotlin.math.absoluteValue

data class CurrencyModel(
    val ID: String,
    val NumCode: String,
    val CharCode: String,
    val Nominal: Int,
    val Name: String,
    val Value: Double,
    val Previous: Double,
    val Date: String
) {
    fun getDifference() = this.Value - this.Previous
    fun getSign() = if (getDifference() > 0) "+" else "-"
    fun getPercentageChange() = (getDifference() / this.Value).absoluteValue * 100
}
