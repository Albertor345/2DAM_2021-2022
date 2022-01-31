package com.example.seriespeliculasflows.data.remote.sources.api.pelis

import com.example.seriespeliculasflows.data.remote.model.PeliculaRemote
import com.example.seriespeliculasflows.data.remote.model.PeliculasResultRemote
import com.example.seriespeliculasflows.data.remote.model.datamappers.toListPeliculaUI
import com.example.seriespeliculasflows.data.remote.model.datamappers.toPeliculaUI
import com.example.seriespeliculasflows.data.remote.BaseApiResponse
import javax.inject.Inject

class PelisDataSource @Inject constructor(private val pelisCalls: PelisCalls) : BaseApiResponse() {

    suspend fun getPeliculas(query: String) = safeApiCall(
        apiCall = { pelisCalls.getPeliculas(query) },
        transform = PeliculasResultRemote::toListPeliculaUI
    )

    suspend fun getPeliculasUpcoming() = safeApiCall(
        apiCall = { pelisCalls.getPeliculasUpcoming() },
        transform = PeliculasResultRemote::toListPeliculaUI
    )

    suspend fun getPelicula(id: Int) = safeApiCall(
        apiCall = { pelisCalls.getPelicula(id) },
        transform = PeliculaRemote::toPeliculaUI
    )

}