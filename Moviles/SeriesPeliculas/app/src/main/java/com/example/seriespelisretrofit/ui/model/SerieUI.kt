package com.example.seriespelisretrofit.ui.model

data class SerieUI(
    val firstAirDate: String?,
    val id: Int,
    val name: String?,
    val originalName: String?,
    val overview: String?,
    val posterPath: String?,
    var favorito: Boolean,
    var seasons: List<TemporadaUI>?
)