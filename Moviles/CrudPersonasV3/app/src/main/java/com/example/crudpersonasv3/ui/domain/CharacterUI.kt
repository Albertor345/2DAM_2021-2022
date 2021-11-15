package com.example.crudpersonasv3.ui.domain

import androidx.room.Entity

@Entity(tableName = "characters")
data class CharacterUI(
    var id: Long?,
    val name: String,
    val description: String,
    val modified: String,
    val image: String,
    var comics: List<ComicUI>,
    var series: List<SerieUI>

) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CharacterUI

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
