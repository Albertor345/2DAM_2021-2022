package com.example.seriespelisretrofit.usecases.peliculas

import com.example.seriespelisretrofit.data.remote.repositories.peliculas.PelisRepository
import javax.inject.Inject

class GetPeliculasUseCase @Inject constructor(private val repository: PelisRepository) {
    suspend fun getPeliculas(query: String) = repository.getPeliculas(query)
    suspend fun getPelicula(id: Int) = repository.getPelicula(id)
}