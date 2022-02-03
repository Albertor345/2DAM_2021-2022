package com.example.seriespeliculasflows.data.local.repositories.favoritos

import com.example.seriespeliculasflows.data.local.model.PeliculaEntity
import com.example.seriespeliculasflows.data.local.model.SerieWithTemporadas
import com.example.seriespeliculasflows.data.local.model.datamappers.toPeliculaUI
import com.example.seriespeliculasflows.data.local.model.datamappers.toSerieUI
import com.example.seriespeliculasflows.data.remote.DataAccessResult
import com.example.seriespeliculasflows.ui.model.ItemUI
import com.example.seriespeliculasflows.ui.model.datamappers.toPeliculaEntity
import com.example.seriespeliculasflows.ui.model.datamappers.toSerieWithTemporadas
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LocalRepository @Inject constructor(private val daoLocal: DaoLocal) {

    fun insertPelicula(pelicula: PeliculaEntity): Flow<DataAccessResult<Long>> {
        return flow {
            emit(DataAccessResult.Success(daoLocal.insertPelicula(pelicula)))
        }.flowOn(Dispatchers.IO)

    }

    fun insertSerie(serie: SerieWithTemporadas): Flow<DataAccessResult<Long>> {
        return flow {
            emit(DataAccessResult.Success(daoLocal.insertSerieWithTemporadas(serie)))
        }.flowOn(Dispatchers.IO)
    }

    suspend fun insertPeliculas(peliculas: List<PeliculaEntity>) = daoLocal.insertPeliculas(peliculas)


    suspend fun getPelicula(id: Int) = daoLocal.getPelicula(id)

    suspend fun getSerie(id: Int) = daoLocal.getSerie(id)

    fun getFavoritos(): Flow<DataAccessResult<List<ItemUI>>> {
        return flow {
            emit(DataAccessResult.Success(daoLocal.getFavoritos().filter { it.favorito }))
        }.flowOn(Dispatchers.IO)
    }

    fun deleteFromFavorito(item: ItemUI): Flow<DataAccessResult<Int>> {
        return flow {
            when (item) {
                is ItemUI.PeliculaUI -> emit(
                    DataAccessResult.Success(
                        daoLocal.deletePelicula(
                            item.toPeliculaEntity()
                        )
                    )
                )
                is ItemUI.SerieUI -> emit(
                    DataAccessResult.Success(
                        daoLocal.deleteSerieWithTemporadas(
                            item.toSerieWithTemporadas()
                        )
                    )
                )
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getPeliculas(): Flow<DataAccessResult<List<ItemUI.PeliculaUI>>> {
        return flow {
            emit(DataAccessResult.Loading())
            emit(DataAccessResult.Success(daoLocal.getPeliculas().map { it.toPeliculaUI() }))
        }.flowOn(Dispatchers.IO)
    }

    suspend fun deleteUltimaQueryPeliculas() = daoLocal.deleteUltimaQueryPeliculas()

    suspend fun deleteUltimaQuerySeries() = daoLocal.deleteUltimaQuerySeries()

    suspend fun insertSeries(series: List<SerieWithTemporadas>) = daoLocal.inserSeriesWithTemporadas(series)

    fun getSeries(): Flow<DataAccessResult<List<ItemUI.SerieUI>>> {
        return flow {
            emit(DataAccessResult.Loading())
            emit(DataAccessResult.Success(daoLocal.getSeries().map { it.toSerieUI() }))
        }.flowOn(Dispatchers.IO)
    }

}