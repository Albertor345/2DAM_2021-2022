package com.example.seriespeliculasflows.usecases.favoritos

import com.example.seriespeliculasflows.data.local.repositories.favoritos.FavoritosLocalRepository
import com.example.seriespeliculasflows.ui.model.PeliculaUI
import com.example.seriespeliculasflows.ui.model.SerieUI
import com.example.seriespeliculasflows.ui.model.datamappers.toPeliculaEntity
import com.example.seriespeliculasflows.ui.model.datamappers.toSerieWithTemporadas
import javax.inject.Inject

class AddToFavoritosUseCase @Inject constructor(val repository: FavoritosLocalRepository) {
    suspend fun addSerieToFavoritos(serie: SerieUI) =
        repository.insertSerie(serie.toSerieWithTemporadas())

    fun addPeliculaToFavoritos(pelicula: PeliculaUI) =
        repository.insertPelicula(pelicula.toPeliculaEntity())

}