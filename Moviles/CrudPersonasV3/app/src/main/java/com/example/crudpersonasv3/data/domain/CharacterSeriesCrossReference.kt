package com.example.crudpersonasv3.data.domain;

import androidx.room.Entity;

@Entity(tableName = "character_serie", primaryKeys = ["id_character", "id_serie"])
data class CharacterSeriesCrossReference(
    val id_character: Int,
    val id_serie: Int
)
