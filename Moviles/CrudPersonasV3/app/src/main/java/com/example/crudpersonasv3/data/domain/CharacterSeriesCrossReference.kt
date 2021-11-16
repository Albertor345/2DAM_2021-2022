package com.example.crudpersonasv3.data.domain;

import androidx.room.Entity;
import androidx.room.ForeignKey

@Entity(
    tableName = "character_serie", primaryKeys = ["id_character", "id_serie"])
data class CharacterSeriesCrossReference(
    val id_character: Long,
    val id_serie: Long
)
