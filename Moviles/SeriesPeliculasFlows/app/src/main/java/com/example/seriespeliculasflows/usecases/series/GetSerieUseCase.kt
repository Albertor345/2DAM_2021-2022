package com.example.seriespeliculasflows.usecases.series

import com.example.seriespeliculasflows.data.remote.repositories.series.SeriesRepository
import javax.inject.Inject

class GetSerieUseCase @Inject constructor(private val repository: SeriesRepository)  {
    suspend fun getSerie(id: Int) = repository.getSerie(id)
}