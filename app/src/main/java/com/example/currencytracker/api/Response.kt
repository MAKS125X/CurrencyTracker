package com.example.currencytracker.api

data class Response(
    val Date: String,
    val PreviousDate: String,
    val PreviousURL: String,
    val Timestamp: String,
    val Valute: Map<String, Currency>
)

