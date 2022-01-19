package com.example.seriespelisretrofit.data.remote.sources.api.temporadas


import com.example.seriespelisretrofit.data.remote.model.SeasonRemote
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SeasonCalls {

    //https://api.themoviedb.org/3/tv/100/season/1?api_key=sdgsd<as&language=en-US

    @GET("tv/{season_id}/season/{season_number}")
    suspend fun getSeason(@Path("season_id") id: Int, @Path("season_number") season_number: Int): Response<SeasonRemote>

}