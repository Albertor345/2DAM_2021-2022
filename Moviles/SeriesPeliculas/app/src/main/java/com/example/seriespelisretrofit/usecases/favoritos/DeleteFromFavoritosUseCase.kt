package com.example.seriespelisretrofit.usecases.favoritos

import com.example.seriespelisretrofit.data.local.model.SerieWithTemporadas
import com.example.seriespelisretrofit.data.local.repositories.favoritos.FavoritosLocalRepository
import com.example.seriespelisretrofit.ui.model.SerieUI
import com.example.seriespelisretrofit.ui.model.datamappers.toSerieWithTemporadas
import javax.inject.Inject

class DeleteFromFavoritosUseCase @Inject constructor(private val favoritosLocalRepository: FavoritosLocalRepository) {
    suspend fun deleteSerieFavoritos(serie: SerieUI) = favoritosLocalRepository.deleteSerie(serie.toSerieWithTemporadas())
}