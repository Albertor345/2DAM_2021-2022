package com.example.seriespelisretrofit.data.remote.sources.api.series

import com.example.seriespelisretrofit.data.remote.BaseApiResponse
import com.example.seriespelisretrofit.data.remote.model.SerieRemote
import com.example.seriespelisretrofit.data.remote.model.SeriesResultRemoteRemote
import com.example.seriespelisretrofit.data.remote.model.datamappers.toListSeriesUI
import com.example.seriespelisretrofit.data.remote.model.datamappers.toSerieUI
import javax.inject.Inject

class SeriesDataSource @Inject constructor(private val seriesCalls: SeriesCalls) :
    BaseApiResponse() {

    suspend fun getSeries(query: String, page: Int) = safeApiCall(
        apiCall = { seriesCalls.getSeries(query, page) },
        transform = SeriesResultRemoteRemote::toListSeriesUI
    )

    suspend fun getSerie(id: Int) = safeApiCall(
        apiCall = { seriesCalls.getSerie(id) },
        transform = SerieRemote::toSerieUI
    )
}