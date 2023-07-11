package com.example.currencytracker.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.currencytracker.repository.SelectedCurrency

@Database(
    version = 1,
    entities = [
        SelectedCurrency::class,
        SettingCurrency::class]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun selectedCurrenciesDao(): SelectedCurrenciesDao
    abstract fun settingsCurrenciesDao(): SettingCurrencyDao
}