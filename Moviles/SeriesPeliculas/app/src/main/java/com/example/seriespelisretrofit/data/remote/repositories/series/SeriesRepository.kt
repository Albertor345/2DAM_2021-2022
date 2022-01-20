package com.example.seriespelisretrofit.data.remote.repositories.series

import com.example.seriespelisretrofit.data.remote.sources.api.series.SeriesDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SeriesRepository @Inject constructor(private val seriesDataSource: SeriesDataSource) {
    suspend fun getSeries(query: String) =
        withContext(Dispatchers.IO)
        { seriesDataSource.getSeries(query) }

    suspend fun getSerie(id: Int) =
        withContext(Dispatchers.IO)
        { seriesDataSource.getSerie(id) }
}