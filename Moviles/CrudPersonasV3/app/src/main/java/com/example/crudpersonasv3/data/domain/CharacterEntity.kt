package com.example.crudpersonasv3.data.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class CharacterEntity(
    @PrimaryKey(autoGenerate = true)
    val id_character: Int,
    val name: String,
    val description: String,
    val modified: String,
    val image: String
)