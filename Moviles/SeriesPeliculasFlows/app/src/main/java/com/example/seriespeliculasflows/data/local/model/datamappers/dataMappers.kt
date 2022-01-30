package com.example.seriespeliculasflows.data.local.model.datamappers

import com.example.seriespeliculasflows.data.local.model.*
import com.example.seriespeliculasflows.ui.model.CapituloUI
import com.example.seriespeliculasflows.ui.model.ItemUI.*
import com.example.seriespeliculasflows.ui.model.TemporadaUI
import com.example.seriespeliculasflows.utils.Constants

fun SerieWithTemporadas.toSerieUI(): SerieUI {
    val serieUI = serie.toSerieUI()
    val temporadas = temporadas?.map { it.toTemporadaUI() }
    serieUI.seasons = temporadas
    return serieUI
}

fun SerieEntity.toSerieUI(): SerieUI =
    SerieUI(id, name, originalName, Constants.IMAGE_PATH + posterPath, overview,firstAirDate, favorito = false, emptyList(), selected = false)

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
    PeliculaUI(id, title, originalTitle, Constants.IMAGE_PATH + posterPath, overview, releaseDate, favorito = false, selected = false)