package com.example.seriespeliculasflows.data.local.repositories.favoritos

import androidx.room.*
import com.example.seriespeliculasflows.data.local.model.*
import com.example.seriespeliculasflows.data.local.model.datamappers.toPeliculaUI
import com.example.seriespeliculasflows.data.local.model.datamappers.toSerieUI
import com.example.seriespeliculasflows.data.remote.DataAccessResult
import com.example.seriespeliculasflows.ui.model.FavoritoUI
import com.example.seriespeliculasflows.ui.model.datamappers.toFavorito
import com.example.seriespeliculasflows.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

@Dao
interface DaoFavoritos {

    @Query("Select * from peliculas")
    fun getPeliculas(): List<PeliculaEntity>

    @Query("Select * from peliculas where id = :id")
    suspend fun getPelicula(id: Int): PeliculaEntity?

    @Query("Select * from series where id = :id")
    suspend fun getSerie(id: Int): SerieEntity?

    @Insert()
    fun insertPelicula(pelicula: PeliculaEntity): Flow<DataAccessResult<Long>>

    @Delete
    suspend fun deletePelicula(pelicula: PeliculaEntity): Flow<DataAccessResult<Int>>

    @Transaction
    @Query("Select * from series")
    fun getSeries(): List<SerieWithTemporadas>

    @Insert()
    suspend fun insertSerie(serie: SerieEntity): Long

    @Insert
    suspend fun insertTemporada(temporadas: TemporadaEntity): Long

    @Insert
    suspend fun insertCapitulo(capitulos: CapituloEntity): Long

    @Delete
    suspend fun deleteSerie(serie: SerieEntity): Int

    @Delete
    suspend fun deleteTemporadas(temporadas: TemporadaEntity): Int

    @Delete
    suspend fun deleteCapitulos(capitulos: CapituloEntity): Int

    @Transaction
    suspend fun insertSerieWithTemporadas(serie: SerieWithTemporadas): Flow<DataAccessResult<String>> {
        insertSerie(serie.serie)
        serie.temporadas?.map { insertTemporada(it.temporada) }
        serie.temporadas?.map { it.capitulos?.map { capitulo -> insertCapitulo(capitulo) } }
        return flow {
            emit(DataAccessResult.Success(Constants.SUCCESS_ADDING_SERIE))
        }
    }

    @Transaction
    suspend fun deleteSerieWithTemporadas(serie: SerieWithTemporadas): Flow<DataAccessResult<String>> {
        return flow {
            serie.temporadas?.map { it.capitulos?.map { capitulo -> deleteCapitulos(capitulo) } }
            serie.temporadas?.map { deleteTemporadas(it.temporada) }
            deleteSerie(serie.serie)
            emit(DataAccessResult.Success(Constants.SUCCESS_REMOVING_SERIE))
        }
    }

    @Transaction
    fun getFavoritos(): Flow<DataAccessResult<List<FavoritoUI>>> {
        return flow {
            emit(DataAccessResult.Success(getPeliculas().map { it.toPeliculaUI() }
                .map { it.toFavorito() } + getSeries().map { it.toSerieUI() }
                .map { it.toFavorito() }))
        }.flowOn(Dispatchers.IO)
    }
}