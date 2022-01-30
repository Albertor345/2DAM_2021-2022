package com.example.seriespeliculasflows.data.local.repositories.favoritos

import com.example.seriespeliculasflows.data.local.model.PeliculaEntity
import com.example.seriespeliculasflows.data.local.model.SerieWithTemporadas
import com.example.seriespeliculasflows.data.remote.DataAccessResult
import com.example.seriespeliculasflows.ui.model.ItemUI
import com.example.seriespeliculasflows.ui.model.datamappers.toPeliculaEntity
import com.example.seriespeliculasflows.ui.model.datamappers.toSerieEntity
import com.example.seriespeliculasflows.ui.model.datamappers.toSerieWithTemporadas
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FavoritosLocalRepository @Inject constructor(private val daoFavoritos: DaoFavoritos) {

    fun insertPelicula(pelicula: PeliculaEntity) =
        daoFavoritos.insertPelicula(pelicula)

    suspend fun deletePelicula(pelicula: PeliculaEntity) =
        daoFavoritos.deletePelicula(pelicula)

    fun insertSerie(serie: SerieWithTemporadas) =
        daoFavoritos.insertSerieWithTemporadas(serie)

    fun deleteSerie(serie: SerieWithTemporadas) =
        daoFavoritos.deleteSerieWithTemporadas(serie)

    suspend fun getPelicula(id: Int) = daoFavoritos.getPelicula(id)

    suspend fun getSerie(id: Int) = daoFavoritos.getSerie(id)

    fun getFavoritos(): Flow<DataAccessResult<List<ItemUI>>> = daoFavoritos.getFavoritos()

    fun deleteFromFavorito(item: ItemUI): Flow<DataAccessResult<Int>> {
        return when(item){
            is ItemUI.PeliculaUI -> daoFavoritos.deletePelicula(item.toPeliculaEntity())
            is ItemUI.SerieUI -> daoFavoritos.deleteSerieWithTemporadas(item.toSerieWithTemporadas())
        }

    }

}