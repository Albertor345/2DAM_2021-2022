package com.example.seriespeliculasflows.data.remote.repositories.temporadas

import com.example.seriespeliculasflows.data.remote.DataAccessResult
import com.example.seriespeliculasflows.data.remote.sources.api.temporadas.TemporadasDataSource
import com.example.seriespeliculasflows.ui.model.TemporadaUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TemporadasRepository @Inject constructor(private val seriesDataSource: TemporadasDataSource) {
    suspend fun getSeason(id: Int, seasonNumber: Int): Flow<DataAccessResult<TemporadaUI>> {
        return flow {
            emit(DataAccessResult.Loading())
            emit(seriesDataSource.getSeason(id, seasonNumber))
        }.flowOn(Dispatchers.IO)
    }


}