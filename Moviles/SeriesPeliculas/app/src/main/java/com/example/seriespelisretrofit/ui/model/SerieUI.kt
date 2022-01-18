package com.example.seriespelisretrofit.ui.model

import com.google.gson.annotations.SerializedName

data class SerieUI(
    val firstAirDate: String?,
    val id: Int,
    val name: String?,
    val originalName: String?,
    val overview: String?,
    val posterPath: String?,
    val seasons: List<TemporadaUI>?
)