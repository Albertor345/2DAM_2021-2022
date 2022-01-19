package com.example.seriespelisretrofit.usecases.temporadas

import com.example.seriespelisretrofit.data.remote.repositories.temporadas.TemporadasRepository
import javax.inject.Inject

class GetTemporadaUseCase @Inject constructor(private val repository: TemporadasRepository) {
    suspend fun getSeason(id: Int, seasonNumber: Int) = repository.getSeason(id, seasonNumber)
}