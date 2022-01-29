package com.example.seriespeliculasflows.usecases.temporadas

import com.example.seriespeliculasflows.data.remote.repositories.temporadas.TemporadasRepository
import javax.inject.Inject

class GetTemporadaUseCase @Inject constructor(private val repository: TemporadasRepository) {
    suspend fun getSeason(id: Int, seasonNumber: Int) = repository.getSeason(id, seasonNumber)
}