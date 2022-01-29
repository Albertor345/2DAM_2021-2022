package com.example.seriespeliculasflows.data.local.model.datamappers

import com.example.seriespeliculasflows.data.local.model.*
import com.example.seriespeliculasflows.ui.model.CapituloUI
import com.example.seriespeliculasflows.ui.model.PeliculaUI
import com.example.seriespeliculasflows.ui.model.SerieUI
import com.example.seriespeliculasflows.ui.model.TemporadaUI

fun SerieWithTemporadas.toSerieUI(): SerieUI {
    val serieUI = serie.toSerie()
    val temporadas = temporadas?.map { it.toTemporadaUI() }
    serieUI.seasons = temporadas
    return serieUI
}

fun SerieEntity.toSerie(): SerieUI =
    SerieUI(firstAirDate, id, name, originalName, overview, posterPath, true, emptyList())

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
    PeliculaUI(id, backdropPath, originalTitle, posterPath, overview, releaseDate, originalTitle, true)