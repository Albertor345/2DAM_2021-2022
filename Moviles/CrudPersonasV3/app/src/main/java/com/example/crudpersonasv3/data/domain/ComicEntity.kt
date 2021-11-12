package com.example.crudpersonasv3.data.domain


import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "comics")
data class ComicEntity(
    @PrimaryKey(autoGenerate = true)
    val id_comic: Int,
    val name: String
)