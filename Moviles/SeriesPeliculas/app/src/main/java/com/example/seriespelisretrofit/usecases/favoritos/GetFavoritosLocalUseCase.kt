package com.example.seriespelisretrofit.usecases.favoritos

import com.example.seriespelisretrofit.data.remote.repositories.peliculas.PelisRepository
import javax.inject.Inject

class GetFavoritosLocalUseCase @Inject constructor(private val repository: PelisRepository) {
    fun getFavoritos() = repository
}