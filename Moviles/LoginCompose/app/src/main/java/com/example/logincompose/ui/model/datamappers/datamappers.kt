package com.example.logincompose.ui.model.datamappers

import com.example.logincompose.data.local.model.*
import com.example.logincompose.ui.model.CapituloUI
import com.example.logincompose.ui.model.ItemUI.PeliculaUI
import com.example.logincompose.ui.model.ItemUI.SerieUI
import com.example.logincompose.ui.model.TemporadaUI

fun SerieUI.toSerieWithTemporadas(): SerieWithTemporadas =
    SerieWithTemporadas(toSerieEntity(), seasons?.map { it.toTemporadaWithCapitulos() })

fun SerieUI.toSerieEntity(): SerieEntity =
    SerieEntity(releaseDate, id, name, originalName, overview, imagePath, favorito)

fun TemporadaUI.toTemporadaWithCapitulos(): TemporadaWithCapitulos =
    TemporadaWithCapitulos(toTemporadaEntity(), capitulos?.map { it.toCapituloEntity(id) })

fun TemporadaUI.toTemporadaEntity(): TemporadaEntity =
    TemporadaEntity(id, idSerie, airDate, episodeCount, name, overview, posterPath, seasonNumber)

fun CapituloUI.toCapituloEntity(idTemporada: Int): CapituloEntity =
    CapituloEntity(episodeNumber, id, name, idTemporada)

fun PeliculaUI.toPeliculaEntity(): PeliculaEntity =
    PeliculaEntity(id, name, originalName, imagePath, overview, releaseDate, name, favorito)
