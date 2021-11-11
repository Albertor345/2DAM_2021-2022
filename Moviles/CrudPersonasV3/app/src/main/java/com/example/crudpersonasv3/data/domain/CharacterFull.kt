package com.example.crudpersonasv3.data.domain

import androidx.room.Embedded
import androidx.room.Relation

data class CharacterFull(
    @Embedded val characterWithComics: CharacterWithComics,
    @Relation(
        entity = CharacterEntity::class,
        parentColumn = "id",
        entityColumn = "id"
    )
    val series: List<SerieEntity>
)