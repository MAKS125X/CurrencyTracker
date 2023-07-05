package com.example.currencytracker.repository

import com.example.currencytracker.api.RetrofitInstance


object ResponseRepository {
    suspend fun getCurrencyList(): com.example.currencytracker.api.Response {
        return RetrofitInstance.apiService.getMyData()
    }
}