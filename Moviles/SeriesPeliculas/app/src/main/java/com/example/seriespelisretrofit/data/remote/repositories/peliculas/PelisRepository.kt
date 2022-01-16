package com.example.seriespelisretrofit.data.remote.repositories.peliculas

import com.example.seriespelisretrofit.data.remote.sources.api.pelis.PelisDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ActivityRetainedScoped
class PelisRepository @Inject constructor(private val pelisDataSource: PelisDataSource) {
    suspend fun getPeliculas(query: String, page: Int) =
        withContext(Dispatchers.IO)
        { pelisDataSource.getPeliculas(query, page) }

    suspend fun getPelicula(id: Int) =
        withContext(Dispatchers.IO)
        { pelisDataSource.getPelicula(id) }
}