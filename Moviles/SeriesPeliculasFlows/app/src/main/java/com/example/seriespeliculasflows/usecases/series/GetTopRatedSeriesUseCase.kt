package com.example.seriespeliculasflows.usecases.series

import com.example.seriespeliculasflows.data.remote.repositories.series.SeriesRepository
import javax.inject.Inject

class GetTopRatedSeriesUseCase @Inject constructor(private val repository: SeriesRepository) {
    suspend fun getSeriesInit(update: Boolean) = repository.getSeriesInit(update)
}