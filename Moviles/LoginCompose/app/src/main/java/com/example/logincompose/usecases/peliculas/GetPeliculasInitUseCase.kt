package com.example.logincompose.usecases.peliculas

import com.example.logincompose.data.remote.repositories.peliculas.PelisRepository
import javax.inject.Inject

class GetPeliculasInitUseCase @Inject constructor(private val repository: PelisRepository) {
    fun getPeliculasInit(update: Boolean) = repository.getPeliculasInit(update)
}