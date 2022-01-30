package com.example.seriespeliculasflows.data.remote.model.datamappers

import com.example.seriespeliculasflows.data.remote.model.*
import com.example.seriespeliculasflows.ui.model.CapituloUI
import com.example.seriespeliculasflows.ui.model.PeliculaUI
import com.example.seriespeliculasflows.ui.model.SerieUI
import com.example.seriespeliculasflows.ui.model.TemporadaUI
import com.example.seriespeliculasflows.utils.Constants


fun PeliculasResultRemote.toListPeliculaUI(): List<PeliculaUI> =
    peliculas?.let { it.map { it.toPeliculaUI() } } ?: emptyList()

fun PeliculaRemote.toPeliculaUI(): PeliculaUI =
    PeliculaUI(
        id, backdropPath, originalTitle,
        Constants.IMAGE_PATH + posterPath, overview, releaseDate, title, false
    )

fun SeriesResultRemoteRemote.toListSeriesUI(): List<SerieUI> =
    series!!.map { it.toSerieUI() }

fun SerieRemote.toSerieUI(): SerieUI =
    SerieUI(
        firstAirDate,
        id,
        name,
        originalName,
        overview,
        Constants.IMAGE_PATH + posterPath, false,
        seasons?.map { it.toTemporadaUI(id) })

fun SeasonRemote.toTemporadaUI(idSerie: Int): TemporadaUI =
    TemporadaUI(
        id,
        idSerie,
        airDate,
        episodeCount,
        name,
        overview,
        posterPath,
        seasonNumber,
        capitulos?.let {
            it.map { it.toCapituloUI() }
        })

fun CapituloRemote.toCapituloUI(): CapituloUI =
    CapituloUI(episodeNumber, id, name)