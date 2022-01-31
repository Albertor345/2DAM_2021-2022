package com.example.seriespeliculasflows.usecases.favoritos

import com.example.seriespeliculasflows.data.local.repositories.favoritos.FavoritosLocalRepository
import com.example.seriespeliculasflows.ui.model.ItemUI
import com.example.seriespeliculasflows.ui.model.datamappers.toPeliculaEntity
import com.example.seriespeliculasflows.ui.model.datamappers.toSerieWithTemporadas
import javax.inject.Inject

class AddToFavoritosUseCase @Inject constructor(val repository: FavoritosLocalRepository) {
    fun addSerieToFavoritos(serie: ItemUI.SerieUI) =
        repository.insertSerie(serie.toSerieWithTemporadas())

    fun addPeliculaToFavoritos(pelicula: ItemUI.PeliculaUI) =
        repository.insertPelicula(pelicula.toPeliculaEntity())

}