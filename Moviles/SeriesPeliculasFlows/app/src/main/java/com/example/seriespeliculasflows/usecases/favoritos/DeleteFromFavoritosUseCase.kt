package com.example.seriespeliculasflows.usecases.favoritos

import com.example.seriespeliculasflows.data.local.repositories.favoritos.FavoritosLocalRepository
import com.example.seriespeliculasflows.ui.model.PeliculaUI
import com.example.seriespeliculasflows.ui.model.SerieUI
import com.example.seriespeliculasflows.ui.model.datamappers.toPeliculaEntity
import com.example.seriespeliculasflows.ui.model.datamappers.toSerieWithTemporadas
import javax.inject.Inject

class DeleteFromFavoritosUseCase @Inject constructor(private val favoritosLocalRepository: FavoritosLocalRepository) {
    suspend fun deleteSerieFavoritos(serie: SerieUI) =
        favoritosLocalRepository.deleteSerie(serie.toSerieWithTemporadas())

    suspend fun deletePeliculaFavoritos(pelicula: PeliculaUI) =
        favoritosLocalRepository.deletePelicula(pelicula.toPeliculaEntity())
}