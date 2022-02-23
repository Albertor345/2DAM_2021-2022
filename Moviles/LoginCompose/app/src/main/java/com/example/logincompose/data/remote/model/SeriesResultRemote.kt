package com.example.logincompose.data.remote.model


import com.example.logincompose.data.remote.model.SerieRemote
import com.google.gson.annotations.SerializedName

data class SeriesResultRemote(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val series: List<SerieRemote>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)