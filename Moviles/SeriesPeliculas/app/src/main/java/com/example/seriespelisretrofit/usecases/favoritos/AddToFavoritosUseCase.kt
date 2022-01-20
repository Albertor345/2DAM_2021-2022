package com.example.seriespelisretrofit.usecases.favoritos

import com.example.seriespelisretrofit.data.local.repositories.favoritos.FavoritosLocalRepository
import com.example.seriespelisretrofit.ui.model.PeliculaUI
import com.example.seriespelisretrofit.ui.model.SerieUI
import com.example.seriespelisretrofit.ui.model.datamappers.toPeliculaEntity
import com.example.seriespelisretrofit.ui.model.datamappers.toSerieWithTemporadas
import javax.inject.Inject

class AddToFavoritosUseCase @Inject constructor(val repository: FavoritosLocalRepository) {
    suspend fun addSerieToFavoritos(serie: SerieUI) =
        repository.insertSerie(serie.toSerieWithTemporadas())

    suspend fun addPeliculaToFavoritos(pelicula: PeliculaUI) =
        repository.insertPelicula(pelicula.toPeliculaEntity())

}