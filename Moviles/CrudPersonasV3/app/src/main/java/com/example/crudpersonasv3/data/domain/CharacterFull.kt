package com.example.crudpersonasv3.data.domain

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class CharacterFull(
    @Embedded val character: CharacterEntity,
    @Relation(
        parentColumn = "id_character",
        entity = SerieEntity::class,
        entityColumn = "id_serie",
        associateBy = Junction(
            value = CharacterSeriesCrossReference::class,
            parentColumn = "id_character",
            entityColumn = "id_serie"
        )
    )
    val series: List<SerieEntity>,
    @Relation(
        parentColumn = "id_character",
        entity = ComicEntity::class,
        entityColumn = "id_comic",
        associateBy = Junction(
            value = CharacterComicsCrossReference::class,
            parentColumn = "id_character",
            entityColumn = "id_comic"
        )
    )
    val comics: List<ComicEntity>

)