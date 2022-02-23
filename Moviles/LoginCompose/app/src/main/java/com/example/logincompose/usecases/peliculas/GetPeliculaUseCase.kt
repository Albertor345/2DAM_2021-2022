package com.example.logincompose.usecases.peliculas

import com.example.logincompose.data.remote.repositories.peliculas.PelisRepository
import javax.inject.Inject

class GetPeliculaUseCase @Inject constructor(private val repository: PelisRepository) {
    fun getPelicula(id: Int) = repository.getPelicula(id)
}