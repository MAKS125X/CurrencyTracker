package com.example.currencytracker.repository

import com.example.currencytracker.api.ResponseNetwork

fun ResponseNetwork.toCurrencyModelList(): MutableList<SelectedCurrency> {
    val list = mutableListOf<SelectedCurrency>()
    for (valuteItem in this.currency) {
        list.add(
            SelectedCurrency(
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
