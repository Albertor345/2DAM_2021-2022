package com.example.seriespeliculasflows.data.remote.repositories.peliculas

import com.example.seriespeliculasflows.data.local.repositories.favoritos.FavoritosLocalRepository
import com.example.seriespeliculasflows.data.remote.DataAccessResult
import com.example.seriespeliculasflows.data.remote.sources.api.pelis.PelisDataSource
import com.example.seriespeliculasflows.ui.model.PeliculaUI
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class PelisRepository @Inject constructor(
    private val pelisDataSource: PelisDataSource,
    private val repository: FavoritosLocalRepository
) {
    fun getPeliculas(query: String): Flow<DataAccessResult<List<PeliculaUI>>> {
        return flow {
            emit(DataAccessResult.Loading())
            emit(pelisDataSource.getPeliculas(query))

        }.flowOn(Dispatchers.IO)
    }

    fun getPelicula(id: Int): Flow<DataAccessResult<PeliculaUI>> {
        return flow {
            emit(DataAccessResult.Loading())
            val pelicula = pelisDataSource.getPelicula(id)
            pelicula.data?.let {
                it.favorito = repository.getPelicula(id) != null
            }
            emit(pelicula)
        }.flowOn(Dispatchers.IO)
    }


}