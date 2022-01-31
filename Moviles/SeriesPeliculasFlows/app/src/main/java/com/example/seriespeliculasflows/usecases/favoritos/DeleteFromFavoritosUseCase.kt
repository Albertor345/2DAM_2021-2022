package com.example.seriespeliculasflows.usecases.favoritos

import com.example.seriespeliculasflows.data.local.repositories.favoritos.FavoritosLocalRepository
import com.example.seriespeliculasflows.ui.model.ItemUI
import javax.inject.Inject

class DeleteFromFavoritosUseCase @Inject constructor(private val favoritosLocalRepository: FavoritosLocalRepository) {
    fun deleteFromFavorito(item: ItemUI) =
        favoritosLocalRepository.deleteFromFavorito(item)
}