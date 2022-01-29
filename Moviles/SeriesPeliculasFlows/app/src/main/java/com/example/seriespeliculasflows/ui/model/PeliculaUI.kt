package com.example.seriespeliculasflows.ui.model

data class PeliculaUI(
    val id: Int,
    val backdropPath: String?,
    val originalTitle: String?,
    val posterPath: String?,
    val overview: String?,
    val releaseDate: String?,
    val title: String?,
    var favorito: Boolean
)
