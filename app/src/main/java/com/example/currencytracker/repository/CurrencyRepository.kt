package com.example.currencytracker.repository

import androidx.room.withTransaction
import com.example.currencytracker.api.ApiService
import com.example.currencytracker.api.CurrencyNetwork
import com.example.currencytracker.api.ResponseNetwork
import com.example.currencytracker.db.AppDatabase
import com.example.currencytracker.db.CurrencyDao
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Inject

@InstallIn(ViewModelComponent::class)
@Module
class CurrencyRepository @Inject constructor(
    private val api: ApiService,
    private val db: AppDatabase
) {

    private val dao: CurrencyDao = db.currencyDao()

    fun getCurrency() = networkBoundResource(
        query = {
            dao.getAllCurrencies()
        },
        fetch = {
            api.getMyData().toCurrencyModelList()
        },
        saveFetchResult = { currencies ->
            db.withTransaction {
                dao.deleteAllCurrencies()
                dao.insertCurrencies(currencies)
            }
        }
    )

    suspend fun testGetCurrencyList(): MutableList<Currency> {
        return ResponseNetwork(
            "2023-07-07T11:30:00+03:00",
            "2023-07-06T11:30:00+03:00",
            "//www.cbr-xml-daily.ru/archive/2023/07/06/daily_json.js",
            "2023-07-06T18:00:00+03:00",
            mapOf(
                "AUD" to CurrencyNetwork(
                    id = "R01010",
                    numCode = "036",
                    charCode = "AUD",
                    nominal = 1,
                    name = "Австралийский доллар",
                    value = 61.6605,
                    previous = 60.4181
                ),
                "AZN" to CurrencyNetwork(
                    id = "R01020A",
                    numCode = "944",
                    charCode = "AZN",
                    nominal = 1,
                    name = "Азербайджанский манат",
                    value = 54.4526,
                    previous = 53.14
                ),
                "HUF" to CurrencyNetwork(
                    id = "R01020A",
                    numCode = "944",
                    charCode = "AZN",
                    nominal = 1,
                    name = "Азербайджанский манат",
                    value = 54.4526,
                    previous = 53.14
                ),
                "USD" to CurrencyNetwork(
                    id = "R01020A",
                    numCode = "944",
                    charCode = "AZN",
                    nominal = 1,
                    name = "Азербайджанский манат",
                    value = 54.4526,
                    previous = 53.14
                ),
                "AUD" to CurrencyNetwork(
                    id = "R01020A",
                    numCode = "944",
                    charCode = "AZN",
                    nominal = 1,
                    name = "Азербайджанский манат",
                    value = 54.4526,
                    previous = 53.14
                ),
                "BRL" to CurrencyNetwork(
                    id = "R01020A",
                    numCode = "944",
                    charCode = "AZN",
                    nominal = 1,
                    name = "Азербайджанский манат",
                    value = 54.4526,
                    previous = 53.14
                ),
                "TRY" to CurrencyNetwork(
                    id = "R01020A",
                    numCode = "944",
                    charCode = "AZN",
                    nominal = 1,
                    name = "Азербайджанский манат",
                    value = 54.4526,
                    previous = 53.14
                ),
                "CAD" to CurrencyNetwork(
                    id = "R01020A",
                    numCode = "944",
                    charCode = "AZN",
                    nominal = 1,
                    name = "Азербайджанский манат",
                    value = 54.4526,
                    previous = 53.14
                ),
                "CHF" to CurrencyNetwork(
                    id = "R01020A",
                    numCode = "944",
                    charCode = "AZN",
                    nominal = 1,
                    name = "Азербайджанский манат",
                    value = 54.4526,
                    previous = 53.14
                ),
                "DKK" to CurrencyNetwork(
                    id = "R01020A",
                    numCode = "944",
                    charCode = "AZN",
                    nominal = 1,
                    name = "Азербайджанский манат",
                    value = 54.4526,
                    previous = 53.14
                )
            ),
        ).toCurrencyModelList()
    }

}