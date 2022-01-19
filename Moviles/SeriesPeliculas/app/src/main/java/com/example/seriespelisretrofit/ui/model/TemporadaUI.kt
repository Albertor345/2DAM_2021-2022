package com.example.seriespelisretrofit.ui.model

data class TemporadaUI(
    val id: Int?,
    val idSerie: Int,
    val airDate: Any?,
    val episodeCount: Int?,
    val name: String?,
    val overview: String?,
    val posterPath: String?,
    val seasonNumber: Int?,
    val capitulos: List<CapituloUI>?
)
