package com.app.moviedemo.data.api

import com.app.moviedemo.data.model.MovieResponse
import com.app.moviedemo.utils.CONSTRAINTS
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService {

    @GET(CONSTRAINTS.BASE_URL)
    suspend fun fetchMovieList(@QueryMap request: HashMap<String, String>): MovieResponse

}