package com.example.currencytracker.repository

import androidx.room.withTransaction
import com.example.currencytracker.api.ApiService
import com.example.currencytracker.db.AppDatabase
import com.example.currencytracker.db.SelectedCurrenciesDao
import com.example.currencytracker.db.SettingCurrency
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.delay
import javax.inject.Inject

@InstallIn(ViewModelComponent::class)
@Module
class CurrencyRepository @Inject constructor(
    private val api: ApiService,
    private val db: AppDatabase
) {

    private val dao: SelectedCurrenciesDao = db.selectedCurrenciesDao()

    fun getCurrency() = networkBoundResource(
        query = {
            dao.getAllCurrencies()
        },
        fetch = {
            api.getMyData().toCurrencyModelList()
        },
        saveFetchResult = { currencies ->
            db.withTransaction {
                dao.insertCurrencies(currencies)
            }
        },
        shouldFetch = {true}
    )

    fun getSettings() = db.settingsCurrenciesDao().getAllCurrencies()

    fun getLevelById(id: String) = db.settingsCurrenciesDao().getNotificationLevelById(id)

    suspend fun updateSettings(settingCurrency: SettingCurrency): Int{
        return db.settingsCurrenciesDao().updateCurrency(settingCurrency)
    }
}