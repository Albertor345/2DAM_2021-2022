package com.example.seriespelisretrofit.data.local.model

import androidx.room.Embedded
import androidx.room.Relation


data class TemporadaWithCapitulos(
    @Embedded
    val temporada: TemporadaEntity,
    @Relation(
        entity = CapituloEntity::class,
        entityColumn = "id_temporada",
        parentColumn = "id"
    )
    val capitulos: List<CapituloEntity>?
)
