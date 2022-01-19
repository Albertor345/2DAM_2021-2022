package com.example.seriespelisretrofit.data.local.repositories.favoritos

import androidx.room.*
import com.example.seriespelisretrofit.data.local.model.*

@Dao
interface DaoFavoritos {

    @Query("Select * from peliculas")
    suspend fun getPeliculas(): List<PeliculaEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPelicula(pelicula: PeliculaEntity): Long

    @Delete
    suspend fun deletePelicula(pelicula: PeliculaEntity): Int

    @Transaction
    @Query("Select * from series")
    suspend fun getSeries(): List<SerieWithTemporadas>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSerie(serie: SerieEntity): Long

    @Insert
    suspend fun insertTemporada(temporadas: TemporadaEntity)

    @Insert
    suspend fun insertCapitulos(capitulos: List<CapituloEntity>)

    @Delete
    suspend fun deleteSerie(serie: SerieEntity): Int

    @Delete
    suspend fun deleteTemporadas(temporadas: TemporadaEntity): Int

    @Delete
    suspend fun deleteCapitulos(capitulos: List<CapituloEntity>): Int

    @Transaction
    suspend fun insertSerieWithTemporadas(serie: SerieWithTemporadas) {
        insertSerie(serie.serie)
        serie.temporadas.map { insertTemporada(it.temporada) }
        serie.temporadas.map { insertCapitulos(it.capitulos) }
    }

    @Transaction
    suspend fun deleteSerieWithTemporadas(serie: SerieWithTemporadas) {
        serie.temporadas.map { deleteCapitulos(it.capitulos) }
        serie.temporadas.map { deleteTemporadas(it.temporada) }
        deleteSerie(serie.serie)
    }
}