package com.example.crudpersonasv3.data.domain

import androidx.room.Embedded
import androidx.room.Relation

data class CharacterWithComics (
    @Embedded val character: CharacterEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    val comics: List<ComicEntity>

)