package com.example.seriespelisretrofit.data.remote.sources.api.series

import com.example.seriespelisretrofit.data.remote.model.SerieRemote
import com.example.seriespelisretrofit.data.remote.model.SeriesResultRemoteRemote
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface SeriesCalls {
    @GET("tv/{tv_id}")
    suspend fun getSerie(@Path("tv_id") id: Int): Response<SerieRemote>

    @GET("search/tv")
    suspend fun getSeries(
        @Query("query") query: String
    ): Response<SeriesResultRemoteRemote>
}
