package com.example.seriespeliculasflows.usecases.favoritos

import com.example.seriespeliculasflows.data.local.repositories.favoritos.FavoritosLocalRepository
import javax.inject.Inject

class GetFavoritosLocalUseCase @Inject constructor(private val repository: FavoritosLocalRepository) {
    suspend fun getPeliculaLocal(id: Int) = repository.getPelicula(id)

    suspend fun getSerieLocal(id: Int) = repository.getSerie(id)

    fun getFavoritos() = repository.getFavoritos()
}