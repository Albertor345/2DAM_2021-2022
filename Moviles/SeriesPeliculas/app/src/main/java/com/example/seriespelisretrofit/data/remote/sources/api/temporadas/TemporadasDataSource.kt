package com.example.seriespelisretrofit.data.remote.sources.api.temporadas

import com.example.seriespelisretrofit.data.remote.BaseApiResponse
import com.example.seriespelisretrofit.data.remote.model.SeasonRemote
import com.example.seriespelisretrofit.data.remote.model.datamappers.toTemporadaUI
import javax.inject.Inject

class TemporadasDataSource @Inject constructor(private val seasonCalls: SeasonCalls) :
    BaseApiResponse() {

    suspend fun getSeason(id: Int, seasonNumber: Int) = safeApiCall(
        apiCall = { seasonCalls.getSeason(id, seasonNumber) },
        transform = {it.toTemporadaUI(id)}
    )

}