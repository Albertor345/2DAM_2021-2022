package com.example.seriespeliculasflows.data.local.model.datamappers

import com.example.logincompose.data.local.model.*
import com.example.logincompose.ui.model.CapituloUI
import com.example.logincompose.ui.model.ItemUI.*
import com.example.logincompose.ui.model.TemporadaUI
import com.example.logincompose.utils.Constants

fun SerieWithTemporadas.toSerieUI(): SerieUI {
    val serieUI = serie.toSerieUI()
    val temporadas = temporadas?.map { it.toTemporadaUI() }
    serieUI.seasons = temporadas
    return serieUI
}

fun SerieEntity.toSerieUI(): SerieUI =
    SerieUI(id, name, originalName, Constants.IMAGE_PATH + posterPath, overview,firstAirDate, favorito, emptyList(), selected = false)

fun TemporadaWithCapitulos.toTemporadaUI(): TemporadaUI {
    val temporada = temporada.toTemporadaUI()
    temporada.capitulos = capitulos?.map { it.toCapituloUI() }
    return temporada
}

fun TemporadaEntity.toTemporadaUI(): TemporadaUI =
    TemporadaUI(
        id,
        idSerie,
        airDate,
        episodeCount,
        name,
        overview,
        posterPath,
        seasonNumber,
        emptyList()
    )

fun CapituloEntity.toCapituloUI(): CapituloUI =
    CapituloUI(episodeNumber, id, name)

fun PeliculaEntity.toPeliculaUI(): PeliculaUI =
    PeliculaUI(id, title, originalTitle, Constants.IMAGE_PATH + posterPath, overview, releaseDate, favorito, selected = false)