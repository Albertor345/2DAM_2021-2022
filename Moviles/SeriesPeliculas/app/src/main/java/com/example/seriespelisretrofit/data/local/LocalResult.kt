package com.example.seriespelisretrofit.data.local

sealed class LocalResult<T>(
    var data: T? = null,
    var error: String? = null
) {
    class Success<T>(data: T) : LocalResult<T>(data)
    class Error<T>(message: String, data: T? = null) : LocalResult<T>(data, message)
}
