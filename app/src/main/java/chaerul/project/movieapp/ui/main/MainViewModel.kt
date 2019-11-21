package chaerul.project.movieapp.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import chaerul.project.movieapp.BuildConfig
import chaerul.project.movieapp.api.model.DataModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class MainViewModel : ViewModel() {

    companion object {
        private const val API_KEY = BuildConfig.API_KEY
    }

    private val dataMovies = MutableLiveData<ArrayList<DataModel>>()
    private val dataTvs = MutableLiveData<ArrayList<DataModel>>()

    fun getAllMovies(): LiveData<ArrayList<DataModel>> {
        return dataMovies
    }

    fun getAllTvShows(): LiveData<ArrayList<DataModel>> {
        return dataTvs
    }

    internal fun setMovies() {
        val client = AsyncHttpClient()
        val listItems = ArrayList<DataModel>()
        val url = "https://api.themoviedb.org/3/discover/movie?api_key=$API_KEY&language=en-US"

        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray
            ) {
                try {
                    val result = String(responseBody)
                    val responseObject = JSONObject(result)
                    val listMovie = responseObject.getJSONArray("results")

                    for (i in 0 until listMovie.length()) {
                        val movie = listMovie.getJSONObject(i)
                        val movieItem = DataModel()
                        movieItem.id = movie.getInt("id")
                        movieItem.photo = movie.getString("poster_path")
                        movieItem.title = movie.getString("title")
                        movieItem.overview = movie.getString("overview")
                        movieItem.releaseDate = movie.getString("release_date")
                        listItems.add(movieItem)
                    }
                    dataMovies.postValue(listItems)
                } catch (e: Exception) {
                    Log.d("Exception", e.message.toString())
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable
            ) {
                Log.d("Error Exception", error.message.toString())
            }
        })
    }

    internal fun setTvs() {
        val client = AsyncHttpClient()
        val listTvs = ArrayList<DataModel>()
        val url = "https://api.themoviedb.org/3/discover/tv?api_key=$API_KEY&language=en-US"

        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray
            ) {
                try {
                    val response = String(responseBody)
                    val responseObject = JSONObject(response)
                    val listTV = responseObject.getJSONArray("results")

                    for (i in 0 until listTV.length()) {
                        val tvItem = listTV.getJSONObject(i)
                        val tvModel = DataModel()
                        tvModel.id = tvItem.getInt("id")
                        tvModel.photo = tvItem.getString("poster_path")
                        tvModel.title = tvItem.getString("name")
                        tvModel.overview = tvItem.getString("overview")
                        tvModel.releaseDate = tvItem.getString("first_air_date")
                        listTvs.add(tvModel)
                    }
                    dataTvs.postValue(listTvs)
                } catch (e: Exception) {
                    Log.d("Exception", e.message.toString())
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable
            ) {
                Log.d("Error Exception", error.message.toString())
            }

        })
    }
}