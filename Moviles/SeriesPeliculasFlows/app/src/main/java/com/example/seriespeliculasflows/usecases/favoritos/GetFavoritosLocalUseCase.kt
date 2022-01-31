package com.example.seriespeliculasflows.usecases.favoritos

import com.example.seriespeliculasflows.data.local.repositories.favoritos.FavoritosLocalRepository
import javax.inject.Inject

class GetFavoritosLocalUseCase @Inject constructor(private val repository: FavoritosLocalRepository) {
    fun getFavoritos() = repository.getFavoritos()
}