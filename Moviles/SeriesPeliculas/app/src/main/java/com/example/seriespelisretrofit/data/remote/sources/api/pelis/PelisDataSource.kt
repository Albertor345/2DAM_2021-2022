package com.example.seriespelisretrofit.data.remote.sources.api.pelis

import com.example.seriespelisretrofit.data.remote.BaseApiResponse
import com.example.seriespelisretrofit.data.remote.model.PeliculaRemote
import com.example.seriespelisretrofit.data.remote.model.SearchResult
import com.example.seriespelisretrofit.data.remote.model.datamappers.toListPeliculasUI
import javax.inject.Inject

class PelisDataSource @Inject constructor(private val pelisCalls: PelisCalls) : BaseApiResponse() {

    suspend fun getPeliculas(query: String, page: Int) = safeApiCall(
        apiCall = { pelisCalls.getPeliculas(query, page) },
        transform = SearchResult::toListPeliculasUI
    )

    suspend fun getPelicula(id: Int) = safeApiCall(
        apiCall = { pelisCalls.getPelicula(id) },
        transform = PeliculaRemote::toPeliculaUI
    )

}