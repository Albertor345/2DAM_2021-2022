package com.example.crudpersonasv3.data.domain


import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "series")
data class SerieEntity(
    @PrimaryKey(autoGenerate = true)
    val id_serie: Int,
    val name: String
)