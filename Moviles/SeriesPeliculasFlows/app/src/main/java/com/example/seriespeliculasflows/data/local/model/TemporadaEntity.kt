package com.example.seriespeliculasflows.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "temporada")
data class TemporadaEntity(
    @PrimaryKey()
    val id: Int,
    val idSerie: Int,
    val airDate: String?,
    val episodeCount: Int?,
    val name: String?,
    val overview: String?,
    val posterPath: String?,
    val seasonNumber: Int?
)
