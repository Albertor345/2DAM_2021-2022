package com.example.seriespelisretrofit.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "capitulos")
data class CapituloEntity(
    val episodeNumber: Int?,
    @PrimaryKey()
    val id: Int,
    val name: String?,
    val id_temporada: Int
)