package com.example.seriespeliculasflows.data.remote

import com.example.seriespeliculasflows.utils.Constants
import retrofit2.Response


abstract class BaseApiResponse {

    suspend fun <T, R> safeApiCall(
        apiCall: suspend () -> Response<R>,
        transform: (R) -> T
    ): DataAccessResult<T> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return DataAccessResult.Success(transform(body))
                }
            }
            return error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(errorMessage: String): DataAccessResult<T> =
        DataAccessResult.Error(Constants.API_CALL_FAILED + errorMessage)



}