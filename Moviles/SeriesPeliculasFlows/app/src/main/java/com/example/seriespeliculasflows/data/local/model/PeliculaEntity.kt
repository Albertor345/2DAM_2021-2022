package com.example.seriespeliculasflows.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "peliculas")
data class PeliculaEntity(
    @PrimaryKey()
    val id: Int,
    val backdropPath: String?,
    val originalTitle: String?,
    val posterPath: String?,
    val overview: String?,
    val releaseDate: String?,
    val title: String?
)
