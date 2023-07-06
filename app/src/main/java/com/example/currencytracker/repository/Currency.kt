package com.example.currencytracker.repository

import kotlin.math.absoluteValue

data class Currency(
    val id: String,
    val numCode: String,
    val charCode: String,
    val nominal: Int,
    val name: String,
    val value: Double,
    val previous: Double,
    val date: String,
) {
    fun getDifference() = this.value - this.previous
    fun getSign() = if (getDifference() > 0) "+" else "-"
    fun getPercentageChange() = (getDifference() / this.value).absoluteValue * 100
}
