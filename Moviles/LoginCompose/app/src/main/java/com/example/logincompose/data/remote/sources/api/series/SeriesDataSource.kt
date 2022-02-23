package com.example.logincompose.data.remote.sources.api.series

import com.example.logincompose.data.remote.model.SerieRemote
import com.example.logincompose.data.remote.model.SeriesResultRemote
import com.example.logincompose.data.remote.model.datamappers.toListSeriesUI
import com.example.logincompose.data.remote.model.datamappers.toSerieUI
import com.example.logincompose.data.remote.BaseApiResponse
import javax.inject.Inject

class SeriesDataSource @Inject constructor(private val seriesCalls: SeriesCalls) :
    BaseApiResponse() {

    suspend fun getSeries(query: String) = safeApiCall(
        apiCall = { seriesCalls.getSeries(query) },
        transform = SeriesResultRemote::toListSeriesUI
    )

    suspend fun getTopRatedSeries() = safeApiCall(
        apiCall = { seriesCalls.getTopRatedSeries() },
        transform = SeriesResultRemote::toListSeriesUI
    )

    suspend fun getSerie(id: Int) = safeApiCall(
        apiCall = { seriesCalls.getSerie(id) },
        transform = SerieRemote::toSerieUI
    )
}