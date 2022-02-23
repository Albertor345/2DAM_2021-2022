package com.example.logincompose.data.local.model

import androidx.room.Embedded
import androidx.room.Relation
import com.example.logincompose.data.local.model.CapituloEntity
import com.example.logincompose.data.local.model.TemporadaEntity


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
