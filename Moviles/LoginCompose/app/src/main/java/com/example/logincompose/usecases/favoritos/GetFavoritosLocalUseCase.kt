package com.example.logincompose.usecases.favoritos

import com.example.logincompose.data.local.repositories.favoritos.LocalRepository
import javax.inject.Inject

class GetFavoritosLocalUseCase @Inject constructor(private val repository: LocalRepository) {
    fun getFavoritos() = repository.getFavoritos()
}