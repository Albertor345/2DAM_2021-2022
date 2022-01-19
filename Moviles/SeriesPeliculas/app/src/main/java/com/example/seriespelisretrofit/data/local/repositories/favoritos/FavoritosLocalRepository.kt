package com.example.seriespelisretrofit.data.local.repositories.favoritos

import com.example.seriespelisretrofit.data.local.model.PeliculaEntity
import com.example.seriespelisretrofit.data.local.model.SerieWithTemporadas
import javax.inject.Inject

class FavoritosLocalRepository @Inject constructor(private val daoFavoritos: DaoFavoritos) {

    suspend fun insertPelicula(pelicula: PeliculaEntity) =
        daoFavoritos.insertPelicula(pelicula)

    suspend fun deletePelicula(pelicula: PeliculaEntity) =
        daoFavoritos.deletePelicula(pelicula)

    suspend fun getPeliculas() = daoFavoritos.getPeliculas()


    suspend fun getSeries() = daoFavoritos.getSeries()

    suspend fun insertSerie(serie: SerieWithTemporadas) =
        daoFavoritos.insertSerieWithTemporadas(serie)

    suspend fun deleteSerie(serie: SerieWithTemporadas) =
        daoFavoritos.deleteSerieWithTemporadas(serie)
}