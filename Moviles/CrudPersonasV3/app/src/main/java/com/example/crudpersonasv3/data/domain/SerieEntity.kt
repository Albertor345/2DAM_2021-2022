package com.example.crudpersonasv3.data.domain


import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "series"/*, foreignKeys = [
    ForeignKey(
        entity = CharacterEntity::class,
        parentColumns = ["id_character"],
        childColumns = ["id_character"]
    )
]*/)
data class SerieEntity(
    @PrimaryKey(autoGenerate = true)
    val id_serie: Int,
    val name: String/*,
    val id_character: Int*/
)