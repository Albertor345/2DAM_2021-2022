package com.example.examen_moviles_1eva_albertogonzalez.data.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "puntos")
data class PuntoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    val longitud: Double,
    val latitud: Double
)