package com.example.seriespelisretrofit.ui.model.datamappers

import com.example.seriespelisretrofit.data.local.model.*
import com.example.seriespelisretrofit.ui.model.*
import com.example.seriespelisretrofit.utils.Constants

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
    FavoritoUI(id, "pelicula", false, Constants.IMAGE_PATH + posterPath, title)

fun SerieUI.toFavorito(): FavoritoUI =
    FavoritoUI(id, "serie", false, Constants.IMAGE_PATH + posterPath, name)