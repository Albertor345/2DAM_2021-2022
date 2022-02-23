package com.example.logincompose.ui.screens.favoritos

import com.example.logincompose.ui.model.ItemUI


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
        val isSelectionModeEnabled: Boolean = false

    )
}