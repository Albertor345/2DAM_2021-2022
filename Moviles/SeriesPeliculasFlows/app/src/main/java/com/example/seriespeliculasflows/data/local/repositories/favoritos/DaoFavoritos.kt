package com.example.seriespeliculasflows.data.local.repositories.favoritos

import androidx.room.*
import com.example.seriespeliculasflows.data.local.model.*
import com.example.seriespeliculasflows.data.local.model.datamappers.toPeliculaUI
import com.example.seriespeliculasflows.data.local.model.datamappers.toSerieUI
import com.example.seriespeliculasflows.ui.model.ItemUI

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
    fun insertPelicula(pelicula: PeliculaEntity): Long

    @Insert()
    suspend fun insertSerie(serie: SerieEntity): Long

    @Insert
    suspend fun insertTemporada(temporadas: TemporadaEntity): Long

    @Insert
    suspend fun insertCapitulo(capitulos: CapituloEntity): Long


    @Delete
    fun deletePelicula(pelicula: PeliculaEntity): Int

    @Delete
    suspend fun deleteSerie(serie: SerieEntity): Int

    @Delete
    suspend fun deleteTemporadas(temporadas: TemporadaEntity): Int

    @Delete
    suspend fun deleteCapitulos(capitulos: CapituloEntity): Int


    @Transaction
    suspend fun insertSerieWithTemporadas(serie: SerieWithTemporadas): Long {
        val id: Long = insertSerie(serie.serie)
        serie.temporadas?.map { insertTemporada(it.temporada) }
        serie.temporadas?.map { it.capitulos?.map { capitulo -> insertCapitulo(capitulo) } }
        return id
    }

    @Transaction
    suspend fun deleteSerieWithTemporadas(serie: SerieWithTemporadas): Int {
        serie.temporadas?.map { it.capitulos?.map { capitulo -> deleteCapitulos(capitulo) } }
        serie.temporadas?.map { deleteTemporadas(it.temporada) }
        return deleteSerie(serie.serie)
    }

    @Transaction
    suspend fun getFavoritos(): List<ItemUI> {
        return getPeliculas().map { it.toPeliculaUI() } + getSeries().map { it.toSerieUI() }
    }
}