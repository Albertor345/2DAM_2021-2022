package com.example.seriespeliculasflows.data.remote

sealed class DataAccessResult<T>(
    var data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : DataAccessResult<T>(data)
    class Error<T>(message: String, data: T? = null) : DataAccessResult<T>(data, message)
    class Loading<T>: DataAccessResult<T>()
}