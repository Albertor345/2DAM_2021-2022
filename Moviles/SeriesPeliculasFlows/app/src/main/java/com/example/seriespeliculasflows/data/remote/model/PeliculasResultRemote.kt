package com.example.seriespeliculasflows.data.remote.model


import com.google.gson.annotations.SerializedName

data class PeliculasResultRemote(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val peliculas: List<PeliculaRemote>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)