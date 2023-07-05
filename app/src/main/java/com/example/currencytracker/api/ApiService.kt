package com.example.currencytracker.api

import retrofit2.http.GET

interface ApiService {
    @GET("daily_json.js")
    suspend fun getMyData(): Response
}