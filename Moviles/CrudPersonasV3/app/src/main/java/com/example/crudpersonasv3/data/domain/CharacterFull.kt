package com.example.crudpersonasv3.data.domain

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class CharacterFull(
    @Embedded val characterWithComics: CharacterWithComics,
    @Relation(
        entity = CharacterEntity::class,
        parentColumn = "id_character",
        entityColumn = "id_serie",
        associateBy = Junction(CharacterSeriesCrossReference::class)
    )
    val series: List<SerieEntity>
)