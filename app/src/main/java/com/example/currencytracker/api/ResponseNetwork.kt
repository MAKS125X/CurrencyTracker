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
) {
//    fun toCurrencyModelList(): MutableList<Currency> {
//        val list = mutableListOf<Currency>()
//        for (valuteItem in this.valute) {
//            list.add(
//                Currency(
//                    valuteItem.value.id,
//                    valuteItem.value.numCode,
//                    valuteItem.value.charCode,
//                    valuteItem.value.nominal,
//                    valuteItem.value.name,
//                    valuteItem.value.value,
//                    valuteItem.value.previous,
//                    this.date
//                )
//            )
//        }
//
//        return list
//    }
}

