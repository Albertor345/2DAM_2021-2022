package com.example.seriespeliculasflows.data.local.repositories.favoritos

import com.example.seriespeliculasflows.data.local.model.PeliculaEntity
import com.example.seriespeliculasflows.data.local.model.SerieWithTemporadas
import com.example.seriespeliculasflows.data.remote.DataAccessResult
import com.example.seriespeliculasflows.ui.model.ItemUI
import com.example.seriespeliculasflows.ui.model.datamappers.toPeliculaEntity
import com.example.seriespeliculasflows.ui.model.datamappers.toSerieWithTemporadas
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FavoritosLocalRepository @Inject constructor(private val daoFavoritos: DaoFavoritos) {

    fun insertPelicula(pelicula: PeliculaEntity): Flow<DataAccessResult<Long>> {
        return flow {
            emit(DataAccessResult.Success(daoFavoritos.insertPelicula(pelicula)))
        }.flowOn(Dispatchers.IO)

    }


    fun insertSerie(serie: SerieWithTemporadas): Flow<DataAccessResult<Long>> {
        return flow {
            emit(DataAccessResult.Success(daoFavoritos.insertSerieWithTemporadas(serie)))
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getPelicula(id: Int) = daoFavoritos.getPelicula(id)

    suspend fun getSerie(id: Int) = daoFavoritos.getSerie(id)

    fun getFavoritos(): Flow<DataAccessResult<List<ItemUI>>> {
        return flow {
            emit(DataAccessResult.Success(daoFavoritos.getFavoritos()))
        }.flowOn(Dispatchers.IO)
    }

    fun deleteFromFavorito(item: ItemUI): Flow<DataAccessResult<Int>> {
        return flow {
            when (item) {
                is ItemUI.PeliculaUI -> emit(
                    DataAccessResult.Success(
                        daoFavoritos.deletePelicula(
                            item.toPeliculaEntity()
                        )
                    )
                )
                is ItemUI.SerieUI -> emit(
                    DataAccessResult.Success(
                        daoFavoritos.deleteSerieWithTemporadas(
                            item.toSerieWithTemporadas()
                        )
                    )
                )
            }
        }.flowOn(Dispatchers.IO)
    }

}