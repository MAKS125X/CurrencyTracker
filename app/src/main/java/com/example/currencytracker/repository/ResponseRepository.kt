package com.example.currencytracker.repository

import android.util.Log
import com.example.currencytracker.api.ResponseNetwork
import com.example.currencytracker.api.RetrofitInstance


object ResponseRepository {

    //получение данных из сети или из бд
    suspend fun getCurrencyList(): ResponseNetwork {
        val aboba = RetrofitInstance.apiService.getMyData()
        for (item in aboba.currency){
            Log.d("Aboba1", item.toString())
        }
        return aboba
    }


}