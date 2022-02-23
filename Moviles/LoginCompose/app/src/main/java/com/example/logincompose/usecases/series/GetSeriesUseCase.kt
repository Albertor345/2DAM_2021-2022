package com.example.logincompose.usecases.series

import com.example.logincompose.data.remote.repositories.series.SeriesRepository
import javax.inject.Inject

class GetSeriesUseCase @Inject constructor(private val repository: SeriesRepository) {
    suspend fun getSeries(query: String) = repository.getSeries(query)
}