package com.example.seriespeliculasflows.usecases.series

import com.example.seriespeliculasflows.data.remote.repositories.series.SeriesRepository
import javax.inject.Inject

class GetSeriesUseCase @Inject constructor(private val repository: SeriesRepository) {
    suspend fun getSeries(query: String) = repository.getSeries(query)
    suspend fun getSerie(id: Int) = repository.getSerie(id)
}