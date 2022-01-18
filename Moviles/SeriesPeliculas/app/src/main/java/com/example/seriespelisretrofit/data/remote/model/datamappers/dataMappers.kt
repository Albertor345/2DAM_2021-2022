package com.example.seriespelisretrofit.data.remote.model.datamappers

import com.example.seriespelisretrofit.data.remote.model.PeliculaRemote
import com.example.seriespelisretrofit.data.remote.model.PeliculasResultRemote
import com.example.seriespelisretrofit.data.remote.model.SerieRemote
import com.example.seriespelisretrofit.data.remote.model.SeriesResultRemoteRemote
import com.example.seriespelisretrofit.ui.model.PeliculaUI
import com.example.seriespelisretrofit.ui.model.SerieUI
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
    SerieUI(firstAirDate, id, name, originalName, overview, Constants.IMAGE_PATH + posterPath)