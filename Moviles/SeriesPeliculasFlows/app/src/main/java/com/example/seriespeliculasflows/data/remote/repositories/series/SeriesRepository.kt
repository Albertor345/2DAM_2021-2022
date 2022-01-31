package com.example.seriespeliculasflows.data.remote.repositories.series

import com.example.seriespeliculasflows.data.local.repositories.favoritos.FavoritosLocalRepository
import com.example.seriespeliculasflows.data.remote.DataAccessResult
import com.example.seriespeliculasflows.data.remote.sources.api.series.SeriesDataSource
import com.example.seriespeliculasflows.ui.model.ItemUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SeriesRepository @Inject constructor(
    private val seriesDataSource: SeriesDataSource,
    private val repository: FavoritosLocalRepository
) {
    suspend fun getSeries(query: String): Flow<DataAccessResult<List<ItemUI.SerieUI>>> {
        return flow {
            emit(DataAccessResult.Loading())
            emit(seriesDataSource.getSeries(query))

        }.flowOn(Dispatchers.IO)
    }


    suspend fun getSerie(id: Int): Flow<DataAccessResult<ItemUI.SerieUI>> {
        return flow {
            emit(DataAccessResult.Loading())
            val serie = seriesDataSource.getSerie(id)
            serie.data?.let {
                it.favorito = repository.getSerie(id) != null
            }
            emit(serie)
        }.flowOn(Dispatchers.IO)
    }
}