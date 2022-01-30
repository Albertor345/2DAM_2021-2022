package com.example.seriespelisretrofit.ui.model

data class SerieUI(
    val id: Int,
    val name: String?,
    val originalName: String?,
    val posterPath: String?,
    val overview: String?,
    val firstAirDate: String?,
    var favorito: Boolean,
    var seasons: List<TemporadaUI>?
)