package com.example.crudpersonasv3.data.domain


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "comics", foreignKeys = [
        ForeignKey(
            entity = CharacterEntity::class,
            parentColumns = ["id_character"],
            childColumns = ["id_character"]
        )
    ]
)
data class ComicEntity(
    @ColumnInfo(name = "id_comic")
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val resourceURI: String,
    val id_character: Int
)