package com.example.seriespelisretrofit.data.local.repositories.favoritos

import androidx.room.*
import com.example.seriespelisretrofit.data.local.LocalResult
import com.example.seriespelisretrofit.data.local.model.*
import com.example.seriespelisretrofit.utils.Constants

@Dao
interface DaoFavoritos {

    @Query("Select * from peliculas")
    suspend fun getPeliculas(): List<PeliculaEntity>

    @Query("Select * from peliculas where id = :id")
    suspend fun getPelicula(id: Int): PeliculaEntity?

    @Query("Select * from series where id = :id")
    suspend fun getSerie(id: Int): SerieEntity?

    @Insert()
    suspend fun insertPelicula(pelicula: PeliculaEntity): Long

    @Delete
    suspend fun deletePelicula(pelicula: PeliculaEntity): Int

    @Transaction
    @Query("Select * from series")
    suspend fun getSeries(): List<SerieWithTemporadas>

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
    suspend fun insertSerieWithTemporadas(serie: SerieWithTemporadas): LocalResult<String> {
        try {
            insertSerie(serie.serie)
            serie.temporadas?.map { insertTemporada(it.temporada) }
            serie.temporadas?.map { it.capitulos?.map { capitulo -> insertCapitulo(capitulo) } }
            return LocalResult.Success(Constants.SUCCESS_ADDING_SERIE)
        } catch (ex: Exception) {
            return LocalResult.Error(ex.message!!, null)
        }
    }

    @Transaction
    suspend fun deleteSerieWithTemporadas(serie: SerieWithTemporadas): LocalResult<String> {
        try {
            serie.temporadas?.map { it.capitulos?.map { capitulo -> deleteCapitulos(capitulo) } }
            serie.temporadas?.map { deleteTemporadas(it.temporada) }
            deleteSerie(serie.serie)
            return LocalResult.Success(Constants.SUCCESS_REMOVING_SERIE)
        } catch (ex: Exception) {
            return LocalResult.Error(ex.message!!, null)
        }
    }
}