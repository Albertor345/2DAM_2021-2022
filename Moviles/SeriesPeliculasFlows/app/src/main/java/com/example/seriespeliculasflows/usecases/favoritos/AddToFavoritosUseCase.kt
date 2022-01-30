package com.example.seriespeliculasflows.usecases.favoritos

import com.example.seriespeliculasflows.data.local.repositories.favoritos.FavoritosLocalRepository
import javax.inject.Inject

class AddToFavoritosUseCase @Inject constructor(val repository: FavoritosLocalRepository) {
    suspend fun addSerieToFavoritos(serie: SerieUI) =
        repository.insertSerie(serie.toSerieWithTemporadas())

    fun addPeliculaToFavoritos(pelicula: PeliculaUI) =
        repository.insertPelicula(pelicula.toPeliculaEntity())

}