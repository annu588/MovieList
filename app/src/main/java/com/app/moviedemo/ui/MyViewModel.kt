package com.app.moviedemo.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.moviedemo.data.api.MovieRepo

import com.app.moviedemo.data.model.MovieResponse
import com.app.moviedemo.utils.Resource
import com.app.moviedemo.data.api.RetrofitBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okio.IOException

class MyViewModel : ViewModel() {
    val repo = MovieRepo(RetrofitBuilder.apiService)
    val data = MutableLiveData<Resource<MovieResponse>>()

    init {
        callMovieListApi()
    }

    private fun callMovieListApi() {
        data.postValue(Resource.loading(null))
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val map = HashMap<String, String>()
                map["apikey"] = "b9bd48a6"
                map["s"] = "Marvel"
                map["type"] = "movie"


//http://www.omdbapi.com/?apikey=b9bd48a6&s=Marvel&type=movie
              data.postValue(Resource.success(  repo.getMovieList(map)))
            } catch (ex: Exception) {
                data.postValue(ex.localizedMessage?.let { Resource.error(it, null) })

            } catch (ex: IOException) {
                data.postValue(Resource.error("No internet connection", null))
            }
        }

    }

}