package com.example.currencytracker.repository

sealed class Resource<T>(
    val data: T? = null,
    val error: Throwable? = null
) {
    class SuccessFromApi<T>(data: T) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(throwable: Throwable, data: T? = null) : Resource<T>(data, throwable)
    class SuccessFromDao<T>(throwable: Throwable, data: T) : Resource<T>(data, throwable)
}