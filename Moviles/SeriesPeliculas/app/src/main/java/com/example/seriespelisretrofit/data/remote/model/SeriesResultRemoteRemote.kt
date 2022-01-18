package com.example.seriespelisretrofit.data.remote.model


import com.google.gson.annotations.SerializedName

data class SeriesResultRemoteRemote(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val series: List<SerieRemote>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)