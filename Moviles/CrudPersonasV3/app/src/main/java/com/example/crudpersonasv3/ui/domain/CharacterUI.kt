package com.example.crudpersonasv3.ui.domain

import androidx.room.Entity

@Entity(tableName = "characters")
data class CharacterUI(
    val id: Int,
    val name: String,
    val description: String,
    val modified: String,
    val image: String,
    var comics: List<ComicUI>,
    var series: List<SerieUI>
)