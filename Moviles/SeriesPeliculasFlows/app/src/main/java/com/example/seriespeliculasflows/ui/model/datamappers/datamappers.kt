package com.example.seriespeliculasflows.ui.model.datamappers

import com.example.seriespeliculasflows.data.local.model.*
import com.example.seriespeliculasflows.ui.model.*
import com.example.seriespeliculasflows.utils.Constants

fun SerieUI.toSerieWithTemporadas(): SerieWithTemporadas =
    SerieWithTemporadas(toSerieEntity(), seasons?.map { it.toTemporadaWithCapitulos() })

fun SerieUI.toSerieEntity(): SerieEntity =
    SerieEntity(firstAirDate, id, name, originalName, overview, posterPath)

fun TemporadaUI.toTemporadaWithCapitulos(): TemporadaWithCapitulos =
    TemporadaWithCapitulos(toTemporadaEntity(), capitulos?.map { it.toCapituloEntity(id) })

fun TemporadaUI.toTemporadaEntity(): TemporadaEntity =
    TemporadaEntity(id, idSerie, airDate, episodeCount, name, overview, posterPath, seasonNumber)

fun CapituloUI.toCapituloEntity(idTemporada: Int): CapituloEntity =
    CapituloEntity(episodeNumber, id, name, idTemporada)

fun PeliculaUI.toPeliculaEntity(): PeliculaEntity =
    PeliculaEntity(id, backdropPath, originalTitle, posterPath, overview, releaseDate, title)

fun PeliculaUI.toFavorito(): FavoritoUI =
    FavoritoUI(id, Constants.PELICULA_TYPE, false, Constants.IMAGE_PATH + posterPath, title)

fun SerieUI.toFavorito(): FavoritoUI =
    FavoritoUI(id, Constants.SERIE_TYPE, false, Constants.IMAGE_PATH + posterPath, name)