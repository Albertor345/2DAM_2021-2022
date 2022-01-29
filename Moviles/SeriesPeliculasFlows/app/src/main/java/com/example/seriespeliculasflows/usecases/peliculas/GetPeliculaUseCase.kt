package com.example.seriespeliculasflows.usecases.peliculas

import com.example.seriespeliculasflows.data.remote.repositories.peliculas.PelisRepository
import javax.inject.Inject

class GetPeliculaUseCase @Inject constructor(private val repository: PelisRepository) {
    fun getPelicula(id: Int) = repository.getPelicula(id)
}