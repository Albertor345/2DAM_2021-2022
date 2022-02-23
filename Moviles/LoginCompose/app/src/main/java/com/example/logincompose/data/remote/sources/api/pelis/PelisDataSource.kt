package com.example.logincompose.data.remote.sources.api.pelis

import com.example.logincompose.data.remote.model.PeliculaRemote
import com.example.logincompose.data.remote.model.PeliculasResultRemote
import com.example.logincompose.data.remote.model.datamappers.toListPeliculaUI
import com.example.logincompose.data.remote.model.datamappers.toPeliculaUI
import com.example.logincompose.data.remote.BaseApiResponse
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