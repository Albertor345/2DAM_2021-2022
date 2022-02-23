package com.example.logincompose.usecases.favoritos

import com.example.logincompose.data.local.repositories.favoritos.LocalRepository
import com.example.logincompose.ui.model.ItemUI
import com.example.logincompose.ui.model.datamappers.toPeliculaEntity
import com.example.logincompose.ui.model.datamappers.toSerieWithTemporadas
import javax.inject.Inject

class AddToFavoritosUseCase @Inject constructor(val repository: LocalRepository) {
    fun addSerieToFavoritos(serie: ItemUI.SerieUI) =
        repository.insertSerie(serie.toSerieWithTemporadas())

    fun addPeliculaToFavoritos(pelicula: ItemUI.PeliculaUI) =
        repository.insertPelicula(pelicula.toPeliculaEntity())

}