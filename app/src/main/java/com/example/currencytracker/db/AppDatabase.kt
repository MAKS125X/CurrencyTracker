package com.example.currencytracker.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.currencytracker.repository.Currency

@Database(entities = [Currency::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao
}