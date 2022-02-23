package com.example.logincompose.ui.screens.detallesSeries

import com.example.logincompose.ui.model.ItemUI


interface DetallesSeriesContract {

    sealed class Event {
        object AddFavorito : Event()
        object DeleteFavorito : Event()
        object GetSerie : Event()

    }

    data class DetallesSeriesScreenStatus(
        val serie: ItemUI.SerieUI? = null,
        val isLoading : Boolean = false,
        val error: String? = null,
        val mensaje: String? = null

    )
}