package com.example.seriespelisretrofit.ui.model

data class PeliculaUI(
    val id: Int,
    val backdropPath: String?,
    val genres: List<Int>?,
    val originalTitle: String?,
    val posterPath: String?,
    val overview: String?,
    val releaseDate: String?,
    val title: String?
)
