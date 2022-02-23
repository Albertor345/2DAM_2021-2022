package com.example.logincompose.usecases.favoritos

import com.example.logincompose.data.local.repositories.favoritos.LocalRepository
import com.example.logincompose.ui.model.ItemUI
import javax.inject.Inject

class DeleteFromFavoritosUseCase @Inject constructor(private val localRepository: LocalRepository) {
    fun deleteFromFavorito(item: ItemUI) =
        localRepository.deleteFromFavorito(item)
}