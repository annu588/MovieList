package com.app.moviedemo.data.api

class MovieRepo(private val apiService: ApiService) {

    suspend fun getMovieList(map: HashMap<String, String>) =apiService.fetchMovieList(map)
}