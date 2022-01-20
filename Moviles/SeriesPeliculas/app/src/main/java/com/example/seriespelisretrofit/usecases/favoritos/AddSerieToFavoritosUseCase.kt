package com.example.seriespelisretrofit.usecases.favoritos

import com.example.seriespelisretrofit.data.local.repositories.favoritos.FavoritosLocalRepository
import com.example.seriespelisretrofit.ui.model.SerieUI
import com.example.seriespelisretrofit.ui.model.datamappers.toSerieWithTemporadas
import javax.inject.Inject

class AddSerieToFavoritosUseCase @Inject constructor(val repository: FavoritosLocalRepository) {
    suspend fun addToFavoritos(serie: SerieUI) =
        repository.insertSerie(serie.toSerieWithTemporadas())

}