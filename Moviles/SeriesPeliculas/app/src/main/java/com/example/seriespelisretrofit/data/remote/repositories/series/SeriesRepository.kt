package com.example.seriespelisretrofit.data.remote.repositories.series

import com.example.seriespelisretrofit.data.remote.sources.api.pelis.PelisDataSource
import com.example.seriespelisretrofit.data.remote.sources.api.series.SeriesDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SeriesRepository @Inject constructor(private val pelisDataSource: SeriesDataSource) {
    suspend fun getSeries(query: String, page: Int) =
        withContext(Dispatchers.IO)
        { pelisDataSource.getSeries(query, page) }

    suspend fun getSerie(id: Int) =
        withContext(Dispatchers.IO)
        { pelisDataSource.getSerie(id) }
}