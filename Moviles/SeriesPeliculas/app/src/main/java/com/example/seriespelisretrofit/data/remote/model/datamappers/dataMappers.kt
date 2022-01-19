package com.example.seriespelisretrofit.data.remote.model.datamappers

import com.example.seriespelisretrofit.data.remote.model.*
import com.example.seriespelisretrofit.ui.model.CapituloUI
import com.example.seriespelisretrofit.ui.model.PeliculaUI
import com.example.seriespelisretrofit.ui.model.SerieUI
import com.example.seriespelisretrofit.ui.model.TemporadaUI
import com.example.seriespelisretrofit.utils.Constants


fun PeliculasResultRemote.toListPeliculaUI(): List<PeliculaUI> =
    peliculas!!.map { it.toPeliculaUI() }

fun PeliculaRemote.toPeliculaUI(): PeliculaUI =
    PeliculaUI(
        id, backdropPath, genres, originalTitle,
        Constants.IMAGE_PATH + posterPath, overview, releaseDate, title
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
        Constants.IMAGE_PATH + posterPath,
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