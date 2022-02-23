package com.example.logincompose.ui.model

sealed class ItemUI(
    val id: Int,
    val name: String?,
    val originalName: String?,
    val imagePath: String?,
    val overview: String?,
    val releaseDate: String?,
    var favorito: Boolean,
    var seasons: List<TemporadaUI>?,
    var selected: Boolean
) {
    class PeliculaUI(
        id: Int,
        name: String?,
        originalName: String?,
        imagePath: String?,
        overview: String?,
        releaseDate: String?,
        favorito: Boolean,
        selected: Boolean
    ) :
        ItemUI(id, name, originalName, imagePath, overview, releaseDate, favorito, null, selected){
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false
            return true
        }

        override fun hashCode(): Int {
            return javaClass.hashCode()
        }
    }

    class SerieUI(
        id: Int,
        name: String?,
        originalName: String?,
        imagePath: String?,
        overview: String?,
        releaseDate: String?,
        favorito: Boolean,
        seasons: List<TemporadaUI>?,
        selected: Boolean
    ) :
        ItemUI(id, name, originalName, imagePath, overview, releaseDate, favorito, seasons, selected){
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false
            return true
        }

        override fun hashCode(): Int {
            return javaClass.hashCode()
        }
    }
}
