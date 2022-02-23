package com.example.logincompose.ui.screens.series

import com.example.logincompose.ui.model.ItemUI


interface SeriesContract {

    sealed class Event {
        object GetSeriesQuery : Event()
        object GetTopRatedSeries : Event()

    }

    data class SeriesScreenStatus(
        val items: List<ItemUI.SerieUI> = emptyList(),
        val selectedItems: MutableList<ItemUI.SerieUI> = mutableListOf(),
        val isLoading : Boolean = false,
        val error: String? = null

    )
}