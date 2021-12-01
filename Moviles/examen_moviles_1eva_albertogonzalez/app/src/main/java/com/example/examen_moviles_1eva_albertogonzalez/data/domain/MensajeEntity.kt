package com.example.examen_moviles_1eva_albertogonzalez.data.domain

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "mensajes", foreignKeys = [ForeignKey(
        entity = PuntoEntity::class,
        parentColumns = ["id"],
        childColumns = ["punto_id"]
    )]
)
data class MensajeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    val autor: String,
    val mensaje: String,
    val punto_id: Long?
)