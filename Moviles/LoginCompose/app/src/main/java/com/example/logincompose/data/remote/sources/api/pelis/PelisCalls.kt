package com.example.logincompose.data.remote.sources.api.pelis

import com.example.logincompose.data.remote.model.PeliculaRemote
import com.example.logincompose.data.remote.model.PeliculasResultRemote
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PelisCalls {

    @GET("movie/{movie_id}")
    suspend fun getPelicula(@Path("movie_id") id: Int): Response<PeliculaRemote>

    @GET("movie/upcoming")
    suspend fun getPeliculasUpcoming(): Response<PeliculasResultRemote>

    @GET("search/movie")
    suspend fun getPeliculas(
        @Query("query") query: String
    ): Response<PeliculasResultRemote>
}
