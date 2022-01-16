package com.example.seriespelisretrofit.data.remote.sources.api.pelis

import com.example.seriespelisretrofit.data.remote.BaseApiResponse
import com.example.seriespelisretrofit.data.remote.model.PeliculaRemote
import javax.inject.Inject

class PelisDataSource @Inject constructor(private val pelisCalls: PelisCalls) : BaseApiResponse() {

    suspend fun getPeliculas(query: String, page: Int) = safeApiCall(
        apiCall = { pelisCalls.getPeliculas(query, page) },
        transform = PeliculaRemote::toPeliculaUI
    )


    suspend fun getPelicula(id: Int) = safeApiCall(
        apiCall = { pelisCalls.getPelicula(id) },
        transform = PeliculaRemote::toPeliculaUI
    )

}