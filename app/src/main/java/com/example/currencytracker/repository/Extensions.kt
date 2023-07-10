package com.example.currencytracker.repository

import com.example.currencytracker.api.ResponseNetwork

fun ResponseNetwork.toCurrencyModelList(): MutableList<Currency> {
    val list = mutableListOf<Currency>()
    for (valuteItem in this.currency) {
        list.add(
            Currency(
                valuteItem.value.id,
                valuteItem.value.numCode,
                valuteItem.value.charCode,
                valuteItem.value.nominal,
                valuteItem.value.name,
                valuteItem.value.value,
                valuteItem.value.previous,
                this.date,
            )
        )
    }

    return list
}

fun Double.format(scale: Int) =
    "%.${scale}f".format(this)
