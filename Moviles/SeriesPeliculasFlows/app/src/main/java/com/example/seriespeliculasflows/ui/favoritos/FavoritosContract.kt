package com.example.seriespeliculasflows.ui.favoritos

import com.example.seriespeliculasflows.ui.model.FavoritoUI


interface FavoritosContract {

    sealed class Event {
        object FetchFavoritos : Event()
        object DeleteFavorito : Event()
        object SeleccionarFavorito : Event()
        object ItemIsSelected : Event()

    }

    data class FavoritosScreenStatus(
        val favoritos: List<FavoritoUI> = emptyList(),
        val isLoading : Boolean = false,
        val error: String? = null,

    )
}