package com.example.currencytracker.repository

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.math.absoluteValue

@Entity(tableName = "selected_currency")
data class SelectedCurrency(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "num_code") val numCode: String,
    @ColumnInfo(name = "char_code") val charCode: String,
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
