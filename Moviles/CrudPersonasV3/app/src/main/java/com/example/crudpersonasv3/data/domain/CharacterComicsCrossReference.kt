package com.example.crudpersonasv3.data.domain

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "character_comic", primaryKeys = ["id_character", "id_comic"], foreignKeys = [
        ForeignKey(
            entity = CharacterEntity::class,
            parentColumns = ["id_character"],
            childColumns = ["id_character"]
        ),
        ForeignKey(
            entity = ComicEntity::class,
            parentColumns = ["id_comic"],
            childColumns = ["id_comic"]
        )
    ]
)
data class CharacterComicsCrossReference(
    val id_character: Int,
    val id_comic: Int
)