package com.example.crudpersonasv3.ui.domain


import androidx.room.Entity

@Entity(tableName = "series")
data class SerieUI(
    val name: String,
    val resourceURI: String
)