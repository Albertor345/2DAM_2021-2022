package com.example.logincompose.data.remote.sources.api.temporadas

import com.example.logincompose.data.remote.model.datamappers.toTemporadaUI
import com.example.logincompose.data.remote.BaseApiResponse
import javax.inject.Inject

class TemporadasDataSource @Inject constructor(private val seasonCalls: SeasonCalls) :
    BaseApiResponse() {

    suspend fun getSeason(id: Int, seasonNumber: Int) = safeApiCall(
        apiCall = { seasonCalls.getSeason(id, seasonNumber) },
        transform = { it.toTemporadaUI(id) }
    )

}