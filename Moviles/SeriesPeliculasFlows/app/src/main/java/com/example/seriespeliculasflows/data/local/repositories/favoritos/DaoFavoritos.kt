package com.example.seriespeliculasflows.data.local.repositories.favoritos

import androidx.room.*
import com.example.seriespeliculasflows.data.local.model.*
import com.example.seriespeliculasflows.data.local.model.datamappers.toPeliculaUI
import com.example.seriespeliculasflows.data.local.model.datamappers.toSerieUI
import com.example.seriespeliculasflows.data.remote.DataAccessResult
import com.example.seriespeliculasflows.ui.model.ItemUI
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

    @Transaction
    @Query("Select * from series")
    fun getSeries(): List<SerieWithTemporadas>


    @Insert()
    fun insertPelicula(pelicula: PeliculaEntity): Flow<DataAccessResult<Long>>

    @Insert()
    suspend fun insertSerie(serie: SerieEntity): Long

    @Insert
    suspend fun insertTemporada(temporadas: TemporadaEntity): Long

    @Insert
    suspend fun insertCapitulo(capitulos: CapituloEntity): Long


    @Delete
    fun deletePelicula(pelicula: PeliculaEntity): Flow<DataAccessResult<Int>>

    @Delete
    suspend fun deleteSerie(serie: SerieEntity): Int

    @Delete
    suspend fun deleteTemporadas(temporadas: TemporadaEntity): Int

    @Delete
    suspend fun deleteCapitulos(capitulos: CapituloEntity): Int


    @Transaction
    fun insertSerieWithTemporadas(serie: SerieWithTemporadas): Flow<DataAccessResult<Int>> {
        return flow {
            insertSerie(serie.serie)
            serie.temporadas?.map { insertTemporada(it.temporada) }
            serie.temporadas?.map { it.capitulos?.map { capitulo -> insertCapitulo(capitulo) } }
            emit(DataAccessResult.Success(1))
        }.flowOn(Dispatchers.IO)
    }

    @Transaction
    fun deleteSerieWithTemporadas(serie: SerieWithTemporadas): Flow<DataAccessResult<Int>> {
        return flow {
            serie.temporadas?.map { it.capitulos?.map { capitulo -> deleteCapitulos(capitulo) } }
            serie.temporadas?.map { deleteTemporadas(it.temporada) }
            deleteSerie(serie.serie)
            emit(DataAccessResult.Success(1))
        }.flowOn(Dispatchers.IO)
    }

    @Transaction
    fun getFavoritos(): Flow<DataAccessResult<List<ItemUI>>> {
        return flow {
            emit(DataAccessResult.Success(
                getPeliculas().map { it.toPeliculaUI() } + getSeries().map { it.toSerieUI() }
            ))
        }.flowOn(Dispatchers.IO)
    }
}