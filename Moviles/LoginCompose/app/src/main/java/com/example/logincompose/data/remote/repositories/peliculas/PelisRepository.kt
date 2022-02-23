package com.example.logincompose.data.remote.repositories.peliculas

import com.example.logincompose.data.local.repositories.favoritos.LocalRepository
import com.example.logincompose.data.remote.DataAccessResult
import com.example.logincompose.data.remote.sources.api.pelis.PelisDataSource
import com.example.logincompose.ui.model.ItemUI
import com.example.logincompose.ui.model.datamappers.toPeliculaEntity
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class PelisRepository @Inject constructor(
    private val pelisDataSource: PelisDataSource,
    private val repository: LocalRepository
) {
    fun getPeliculas(query: String): Flow<DataAccessResult<List<ItemUI.PeliculaUI>>> {
        return flow {
            emit(DataAccessResult.Loading())
            repository.deleteUltimaQueryPeliculas()
            pelisDataSource.getPeliculas(query).data?.let {
                repository.insertPeliculas(it.map {pelicula -> pelicula.toPeliculaEntity() })
                emit(DataAccessResult.Success(it))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getPelicula(id: Int): Flow<DataAccessResult<ItemUI.PeliculaUI>> {
        return flow {
            emit(DataAccessResult.Loading())
            val pelicula = pelisDataSource.getPelicula(id)
            pelicula.data?.let {
                it.favorito = repository.getPelicula(id) != null
            }
            emit(pelicula)
        }.flowOn(Dispatchers.IO)
    }

    fun getPeliculasInit(update: Boolean): Flow<DataAccessResult<List<ItemUI.PeliculaUI>>> {
        return flow {
            emit(DataAccessResult.Loading())
            if (update) {
                emit(pelisDataSource.getPeliculasUpcoming())
            } else {
                repository.getPeliculas().collect { result ->
                    when (result) {
                        is DataAccessResult.Error -> TODO()
                        is DataAccessResult.Loading -> emit(result)
                        is DataAccessResult.Success -> {
                            result.data?.let {
                                if (it.isEmpty()) {
                                    emit(pelisDataSource.getPeliculasUpcoming())
                                } else {
                                    val peliculas = it.filter { pelicula -> !pelicula.favorito }
                                    if (!peliculas.isEmpty()) {
                                       emit(DataAccessResult.Success(peliculas))
                                    }else{
                                        emit(pelisDataSource.getPeliculasUpcoming())
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }.flowOn(Dispatchers.IO)
    }


}