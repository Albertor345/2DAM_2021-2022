package com.example.seriespeliculasflows.data.local.model

import androidx.room.Embedded
import androidx.room.Relation

data class SerieWithTemporadas(
    @Embedded
    val serie: SerieEntity,
    @Relation(
        entity = TemporadaEntity::class,
        entityColumn = "idSerie",
        parentColumn = "id"
    )
    val temporadas: List<TemporadaWithCapitulos>?
)
