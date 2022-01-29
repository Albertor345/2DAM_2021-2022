package com.example.seriespeliculasflows.data.local.repositories.favoritos

import com.example.seriespeliculasflows.data.local.model.PeliculaEntity
import com.example.seriespeliculasflows.data.local.model.SerieWithTemporadas
import com.example.seriespeliculasflows.data.remote.DataAccessResult
import com.example.seriespeliculasflows.ui.model.FavoritoUI
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoritosLocalRepository @Inject constructor(private val daoFavoritos: DaoFavoritos) {

    fun insertPelicula(pelicula: PeliculaEntity) =
        daoFavoritos.insertPelicula(pelicula)

    suspend fun deletePelicula(pelicula: PeliculaEntity) =
        daoFavoritos.deletePelicula(pelicula)

    suspend fun insertSerie(serie: SerieWithTemporadas) =
        daoFavoritos.insertSerieWithTemporadas(serie)

    suspend fun deleteSerie(serie: SerieWithTemporadas) =
        daoFavoritos.deleteSerieWithTemporadas(serie)

    suspend fun getPelicula(id: Int) = daoFavoritos.getPelicula(id)

    suspend fun getSerie(id: Int) = daoFavoritos.getSerie(id)

    fun getFavoritos(): Flow<DataAccessResult<List<FavoritoUI>>> = daoFavoritos.getFavoritos()

}