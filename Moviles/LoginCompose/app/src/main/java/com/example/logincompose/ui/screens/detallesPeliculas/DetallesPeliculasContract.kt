package com.example.logincompose.ui.screens.detallesPeliculas

import com.example.logincompose.ui.model.ItemUI


interface DetallesPeliculasContract {

    sealed class Event {
        object AddFavorito : Event()
        object DeleteFavorito : Event()
        object GetPelicula : Event()

    }

    data class DetallesPeliculasScreenStatus(
        val pelicula: ItemUI.PeliculaUI? = null,
        val isLoading : Boolean = false,
        val error: String? = null,
        val mensaje: String? = null

    )
}