package com.example.logincompose.usecases.series

import com.example.logincompose.data.remote.repositories.series.SeriesRepository
import javax.inject.Inject

class GetTopRatedSeriesUseCase @Inject constructor(private val repository: SeriesRepository) {
    suspend fun getSeriesInit(update: Boolean) = repository.getSeriesInit(update)
}