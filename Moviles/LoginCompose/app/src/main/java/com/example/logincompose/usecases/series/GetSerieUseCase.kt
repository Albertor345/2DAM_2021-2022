package com.example.logincompose.usecases.series

import com.example.logincompose.data.remote.repositories.series.SeriesRepository
import javax.inject.Inject

class GetSerieUseCase @Inject constructor(private val repository: SeriesRepository)  {
    suspend fun getSerie(id: Int) = repository.getSerie(id)
}