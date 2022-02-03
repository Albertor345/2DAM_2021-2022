package com.example.seriespeliculasflows.data.remote.repositories.series

import com.example.seriespeliculasflows.data.local.repositories.favoritos.LocalRepository
import com.example.seriespeliculasflows.data.remote.DataAccessResult
import com.example.seriespeliculasflows.data.remote.sources.api.series.SeriesDataSource
import com.example.seriespeliculasflows.ui.model.ItemUI
import com.example.seriespeliculasflows.ui.model.datamappers.toSerieWithTemporadas
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SeriesRepository @Inject constructor(
    private val seriesDataSource: SeriesDataSource,
    private val repository: LocalRepository
) {
    suspend fun getSeries(query: String): Flow<DataAccessResult<List<ItemUI.SerieUI>>> {
        return flow {
            emit(DataAccessResult.Loading())
            repository.deleteUltimaQuerySeries()
            seriesDataSource.getSeries(query).data?.let {
                repository.insertSeries(it.map { serie -> serie.toSerieWithTemporadas() })
                emit(DataAccessResult.Success(it))
            }

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

    fun getSeriesInit(update: Boolean): Flow<DataAccessResult<List<ItemUI.SerieUI>>> {
        return flow {
            emit(DataAccessResult.Loading())
            if (update) {
                emit(seriesDataSource.getTopRatedSeries())
            } else {
                repository.getSeries().collect { result ->
                    when (result) {
                        is DataAccessResult.Error -> TODO()
                        is DataAccessResult.Loading -> emit(result)
                        is DataAccessResult.Success -> {
                            result.data?.let {
                                if (it.isEmpty()) {
                                    emit(seriesDataSource.getTopRatedSeries())
                                } else {
                                    val series = it.filter { serie -> !serie.favorito }
                                    if (!series.isEmpty()) {
                                        emit(DataAccessResult.Success(series))
                                    } else {
                                        emit(seriesDataSource.getTopRatedSeries())
                                    }
                                }

                            }
                        }
                    }
                }
            }
        }.flowOn(Dispatchers.IO)
    }
}