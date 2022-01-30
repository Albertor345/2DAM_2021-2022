package com.example.seriespeliculasflows.ui.favoritos

import com.example.seriespeliculasflows.ui.model.ItemUI


interface FavoritosContract {

    sealed class Event {
        object GetFavoritos : Event()
        object DeleteFavorito : Event()
        object SeleccionarFavorito : Event()
        object ItemIsSelected : Event()

    }

    data class FavoritosScreenStatus(
        val items: List<ItemUI> = emptyList(),
        val selectedItems: MutableList<ItemUI> = mutableListOf(),
        val isLoading : Boolean = false,
        val error: String? = null,
        val isSelected: Boolean = false

    )
}