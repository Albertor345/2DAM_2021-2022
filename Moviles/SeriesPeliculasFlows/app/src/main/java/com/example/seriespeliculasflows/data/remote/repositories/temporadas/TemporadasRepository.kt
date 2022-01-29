package com.example.seriespeliculasflows.data.remote.repositories.temporadas

import com.example.seriespeliculasflows.data.remote.sources.api.temporadas.TemporadasDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TemporadasRepository @Inject constructor(private val seriesDataSource: TemporadasDataSource) {
    suspend fun getSeason(id: Int, seasonNumber: Int) =
        withContext(Dispatchers.IO)
        { seriesDataSource.getSeason(id, seasonNumber) }

}