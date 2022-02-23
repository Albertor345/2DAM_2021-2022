package com.example.logincompose.data.remote.model.datamappers

import com.example.logincompose.data.remote.model.*
import com.example.logincompose.ui.model.CapituloUI
import com.example.logincompose.ui.model.ItemUI.PeliculaUI
import com.example.logincompose.ui.model.ItemUI.SerieUI
import com.example.logincompose.ui.model.TemporadaUI
import com.example.logincompose.utils.Constants


fun PeliculasResultRemote.toListPeliculaUI(): List<PeliculaUI> =
    peliculas?.let { it.map { it.toPeliculaUI() } } ?: emptyList()

fun PeliculaRemote.toPeliculaUI(): PeliculaUI =
    PeliculaUI(
        id,
        title,
        originalTitle,
        Constants.IMAGE_PATH + posterPath,
        overview, releaseDate,
        false,
        false
    )

fun SeriesResultRemote.toListSeriesUI(): List<SerieUI> =
    series!!.map { it.toSerieUI() }

fun SerieRemote.toSerieUI(): SerieUI =
    SerieUI(
        id,
        name,
        originalName,
        Constants.IMAGE_PATH + posterPath,
        overview,
        firstAirDate,
        false,
        seasons?.map { it.toTemporadaUI(id) },
        false
    )

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