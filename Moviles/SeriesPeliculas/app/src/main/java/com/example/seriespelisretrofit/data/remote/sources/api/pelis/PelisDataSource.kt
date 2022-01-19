package com.example.seriespelisretrofit.data.remote.sources.api.pelis

import com.example.seriespelisretrofit.data.remote.BaseApiResponse
import com.example.seriespelisretrofit.data.remote.model.PeliculaRemote
import com.example.seriespelisretrofit.data.remote.model.PeliculasResultRemote
import com.example.seriespelisretrofit.data.remote.model.datamappers.toListPeliculaUI
import com.example.seriespelisretrofit.data.remote.model.datamappers.toPeliculaUI
import javax.inject.Inject

class PelisDataSource @Inject constructor(private val pelisCalls: PelisCalls) : BaseApiResponse() {

    suspend fun getPeliculas(query: String) = safeApiCall(
        apiCall = { pelisCalls.getPeliculas(query) },
        transform = PeliculasResultRemote::toListPeliculaUI
    )

    suspend fun getPelicula(id: Int) = safeApiCall(
        apiCall = { pelisCalls.getPelicula(id) },
        transform = PeliculaRemote::toPeliculaUI
    )

}