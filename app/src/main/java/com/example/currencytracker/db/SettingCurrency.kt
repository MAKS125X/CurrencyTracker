package com.example.currencytracker.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "setting_currency")
data class SettingCurrency(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "char_code") val charCode: String,
    val name: String,
    var isSelected: Boolean,
    @ColumnInfo(name = "notification_level") var notificationLevel: Double,
)