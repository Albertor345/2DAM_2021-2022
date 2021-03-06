package com.example.logincompose.data.remote.model


import com.example.logincompose.data.remote.model.CapituloRemote
import com.google.gson.annotations.SerializedName

data class SeasonRemote(
    @SerializedName("id")
    val id: Int,
    @SerializedName("air_date")
    val airDate: String?,
    @SerializedName("episode_count")
    val episodeCount: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("season_number")
    val seasonNumber: Int?,
    @SerializedName("episodes")
    val capitulos: List<CapituloRemote>?
)