package com.example.crudpersonasv3.data.domain;

import androidx.room.Entity;
import androidx.room.ForeignKey

@Entity(
    tableName = "character_serie", primaryKeys = ["id_character", "id_serie"], foreignKeys = [
        ForeignKey(
            entity = CharacterEntity::class,
            parentColumns = ["id_character"],
            childColumns = ["id_character"]
        ),
        ForeignKey(
            entity = SerieEntity::class,
            parentColumns = ["id_comic"],
            childColumns = ["id_comic"]
        )
    ]
)
data class CharacterSeriesCrossReference(
    val id_character: Int,
    val id_serie: Int
)
