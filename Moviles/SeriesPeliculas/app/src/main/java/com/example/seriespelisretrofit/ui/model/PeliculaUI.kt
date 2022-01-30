package com.example.seriespelisretrofit.ui.model

data class PeliculaUI(
    val id: Int,
    val title: String?,
    val originalTitle: String?,
    val posterPath: String?,
    val backdropPath: String?,
    val overview: String?,
    val releaseDate: String?,
    var favorito: Boolean
)
