package com.example.seriespeliculasflows.ui.peliculas

import com.example.seriespeliculasflows.ui.model.ItemUI


interface PeliculasContract {

    sealed class Event {
        object GetPeliculasQuery : Event()
        object GetPeliculasUpcoming : Event()

    }

    data class PeliculasScreenStatus(
        val items: List<ItemUI.PeliculaUI> = emptyList(),
        val selectedItems: MutableList<ItemUI.PeliculaUI> = mutableListOf(),
        val isLoading : Boolean = false,
        val error: String? = null

    )
}