package com.example.seriespeliculasflows.usecases.peliculas

import com.example.seriespeliculasflows.data.remote.repositories.peliculas.PelisRepository
import javax.inject.Inject

class GetPeliculasUseCase @Inject constructor(private val repository: PelisRepository) {
    fun getPeliculas(query: String) = repository.getPeliculas(query)
}