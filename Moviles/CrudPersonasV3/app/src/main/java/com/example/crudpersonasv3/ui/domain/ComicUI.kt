package com.example.crudpersonasv3.ui.domain


import androidx.room.Entity

@Entity(tableName = "comics")
data class ComicUI(
    val name: String,
    val resourceURI: String
)