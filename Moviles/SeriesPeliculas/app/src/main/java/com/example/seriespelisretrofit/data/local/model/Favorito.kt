package com.example.seriespelisretrofit.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favoritos")
data class Favorito(
    @PrimaryKey(autoGenerate = true)
    val id: Int
)
