package com.example.seriespelisretrofit.data.remote.sources.api.temporadas


import com.example.seriespelisretrofit.data.remote.model.SeasonRemote
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SeasonCalls {

    @GET("tv/{tv_id}/season/{season_number}")
    suspend fun getSeason(@Path("tv_id") id: Int, @Path("season_number") season_number: Int): Response<SeasonRemote>

}