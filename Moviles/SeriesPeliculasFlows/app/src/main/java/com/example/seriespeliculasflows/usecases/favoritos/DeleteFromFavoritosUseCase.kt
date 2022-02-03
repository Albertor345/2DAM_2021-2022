package com.example.seriespeliculasflows.usecases.favoritos

import com.example.seriespeliculasflows.data.local.repositories.favoritos.LocalRepository
import com.example.seriespeliculasflows.ui.model.ItemUI
import javax.inject.Inject

class DeleteFromFavoritosUseCase @Inject constructor(private val localRepository: LocalRepository) {
    fun deleteFromFavorito(item: ItemUI) =
        localRepository.deleteFromFavorito(item)
}