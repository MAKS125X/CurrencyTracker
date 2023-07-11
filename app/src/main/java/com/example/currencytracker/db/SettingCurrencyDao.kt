package com.example.currencytracker.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface SettingCurrencyDao {
    @Query("Select * FROM setting_currency Order by id")
    fun getAllCurrencies(): Flow<List<SettingCurrency>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrencies(currencies: List<SettingCurrency>)

    @Update
    suspend fun updateCurrency(currency: SettingCurrency): Int

    @Query("Select setting_currency.notification_level FROM setting_currency " +
            "WHERE setting_currency.id == :id")
    fun getNotificationLevelById(id: String): Double
}