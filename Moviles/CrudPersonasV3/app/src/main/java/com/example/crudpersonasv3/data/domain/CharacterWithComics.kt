package com.example.crudpersonasv3.data.domain

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class CharacterWithComics(
    @Embedded val character: CharacterEntity,
    @Relation(
        parentColumn = "id_character",
        entityColumn = "id_comic",
        associateBy = Junction(CharacterComicsCrossReference::class)
    )
    val comics: List<ComicEntity>

)