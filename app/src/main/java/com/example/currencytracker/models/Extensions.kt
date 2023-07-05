package com.example.currencytracker.models

import com.example.currencytracker.api.Response

fun Response.toCurrencyModelList(): MutableList<CurrencyModel> {
    val list = mutableListOf<CurrencyModel>()
    for (valuteItem in this.Valute) {
        list.add(
            CurrencyModel(
                valuteItem.value.ID,
                valuteItem.value.NumCode,
                valuteItem.value.CharCode,
                valuteItem.value.Nominal,
                valuteItem.value.Name,
                valuteItem.value.Value,
                valuteItem.value.Previous,
                this.Date,
            )
        )
    }

    return list
}

fun Double.format(scale: Int) =
    "%.${scale}f".format(this)
