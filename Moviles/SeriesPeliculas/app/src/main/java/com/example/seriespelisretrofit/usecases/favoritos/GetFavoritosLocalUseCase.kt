package com.example.seriespelisretrofit.usecases.favoritos

import com.example.seriespelisretrofit.data.local.repositories.favoritos.FavoritosLocalRepository
import com.example.seriespelisretrofit.data.remote.repositories.peliculas.PelisRepository
import javax.inject.Inject

class GetFavoritosLocalUseCase @Inject constructor(private val repository: FavoritosLocalRepository) {
    suspend fun getPeliculaLocal(id: Int) = repository.getPelicula(id)

    suspend fun getSerieLocal(id: Int) = repository.getSerie(id)

    suspend fun getSeriesLocal() = repository.getSeries()

    suspend fun getPeliculasLocal() = repository.getPeliculas()
}