package com.example.seriespeliculasflows.ui.detallesPeliculas


interface DetallesPeliculasContract {

    sealed class Event {
        object AddFavorito : Event()
        object DeleteFavorito : Event()
        object GetPelicula : Event()

    }

    data class DetallesPeliculasScreenStatus(
        val pelicula: PeliculaUI? = null,
        val isLoading : Boolean = false,
        val error: String? = null,
        val mensaje: String? = null

    )
}