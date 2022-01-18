package com.example.seriespelisretrofit.usecases.series

import com.example.seriespelisretrofit.data.remote.repositories.series.SeriesRepository
import javax.inject.Inject

class GetSeriesUseCase @Inject constructor(private val repository: SeriesRepository) {
    suspend fun getSeries(query: String, page: Int) = repository.getSeries(query, page)
    suspend fun getSerie(id: Int) = repository.getSerie(id)
}