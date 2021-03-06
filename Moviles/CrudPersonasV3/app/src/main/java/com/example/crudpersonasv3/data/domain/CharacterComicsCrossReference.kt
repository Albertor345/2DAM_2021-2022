package com.example.crudpersonasv3.data.domain

import androidx.room.Entity

@Entity(
    tableName = "character_comic", primaryKeys = ["id_character", "id_comic"]
)
data class CharacterComicsCrossReference(
    val id_character: Long,
    val id_comic: Long
)