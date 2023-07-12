package com.example.currencytracker.api

import com.google.gson.annotations.SerializedName

data class ResponseNetwork(
    @SerializedName("Date")
    val date: String,
    @SerializedName("PreviousDate")
    val previousDate: String,
    @SerializedName("PreviousURL")
    val previousURL: String,
    @SerializedName("Timestamp")
    val timestamp: String,
    @SerializedName("Valute")
    val currency: Map<String, CurrencyNetwork>
)

