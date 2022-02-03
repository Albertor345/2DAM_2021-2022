package com.example.seriespeliculasflows.usecases.peliculas

import com.example.seriespeliculasflows.data.remote.repositories.peliculas.PelisRepository
import javax.inject.Inject

class GetPeliculasInitUseCase @Inject constructor(private val repository: PelisRepository) {
    fun getPeliculasInit(update: Boolean) = repository.getPeliculasInit(update)
}