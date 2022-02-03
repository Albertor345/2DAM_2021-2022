package com.example.seriespeliculasflows.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "series")
data class SerieEntity(
    val firstAirDate: String?,
    @PrimaryKey()
    val id: Int,
    val name: String?,
    val originalName: String?,
    val overview: String?,
    val posterPath: String?,
    val favorito: Boolean
)