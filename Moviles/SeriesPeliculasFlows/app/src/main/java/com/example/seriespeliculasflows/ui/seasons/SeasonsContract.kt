package com.example.seriespeliculasflows.ui.seasons

import com.example.seriespeliculasflows.ui.model.ItemUI
import com.example.seriespeliculasflows.ui.model.TemporadaUI


interface SeasonsContract {

    sealed class Event {
        object GetSeason : Event()
    }

    data class SeasonsScreenStatus(
        val season: TemporadaUI? = null,
        val selectedItems: MutableList<ItemUI.PeliculaUI> = mutableListOf(),
        val isLoading : Boolean = false,
        val error: String? = null

    )
}