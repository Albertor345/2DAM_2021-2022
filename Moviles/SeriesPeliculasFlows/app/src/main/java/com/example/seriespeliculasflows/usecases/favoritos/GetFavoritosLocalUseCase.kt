package com.example.seriespeliculasflows.usecases.favoritos

import com.example.seriespeliculasflows.data.local.repositories.favoritos.LocalRepository
import javax.inject.Inject

class GetFavoritosLocalUseCase @Inject constructor(private val repository: LocalRepository) {
    fun getFavoritos() = repository.getFavoritos()
}