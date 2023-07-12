package com.example.currencytracker.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.currencytracker.repository.SelectedCurrency
import kotlinx.coroutines.flow.Flow

@Dao
interface SelectedCurrenciesDao {
    @Query("SELECT * FROM selected_currency JOIN setting_currency " +
            "ON selected_currency.id = setting_currency.id " +
            "Where setting_currency.isSelected = 1")
    fun getAllCurrencies(): Flow<List<SelectedCurrency>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrencies(currencies: List<SelectedCurrency>)

    @Query("Delete FROM selected_currency")
    fun deleteAllCurrencies(): Int

    @Query("SELECT * FROM selected_currency JOIN setting_currency " +
            "ON selected_currency.id = setting_currency.id " +
            "WHERE setting_currency.isSelected = 1 " +
            "AND (selected_currency.value > setting_currency.notification_level " +
            "AND selected_currency.previous < setting_currency.notification_level " +
            "OR selected_currency.value < setting_currency.notification_level " +
            "AND selected_currency.previous > setting_currency.notification_level)")
    fun getCurrenciesReachedLevel(): List<SelectedCurrency>
}