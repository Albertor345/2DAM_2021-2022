package com.example.examen_moviles_1eva_albertogonzalez.data.domain

import androidx.room.Embedded
import androidx.room.Relation

data class PuntoWithMensajes(
    @Embedded
    val punto: PuntoEntity,
    @Relation(
        entity = MensajeEntity::class,
        parentColumn = "id",
        entityColumn = "id",
    )
    val mensajes: List<MensajeEntity>
)