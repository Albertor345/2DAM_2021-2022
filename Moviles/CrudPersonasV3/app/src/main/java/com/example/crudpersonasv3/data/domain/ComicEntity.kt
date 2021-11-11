package com.example.crudpersonasv3.data.domain


import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "comics", foreignKeys = [
        ForeignKey(
            entity = CharacterEntity::class,
            parentColumns = ["id"],
            childColumns = ["id"]
        )
    ]
)
data class ComicEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val resourceURI: String,
    val id_character: Int
)