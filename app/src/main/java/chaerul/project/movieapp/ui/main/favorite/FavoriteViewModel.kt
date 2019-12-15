package chaerul.project.movieapp.ui.main.favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import chaerul.project.movieapp.BuildConfig
import chaerul.project.movieapp.MovieApplication
import chaerul.project.movieapp.api.model.DataModel
import chaerul.project.movieapp.database.MovieHelper
import chaerul.project.movieapp.database.TVHelper
import chaerul.project.movieapp.helper.MappingHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class FavoriteViewModel : ViewModel() {
    private var movieHelper: MovieHelper = MovieApplication.movieHelper
    private var tvHelper: TVHelper = MovieApplication.tvHelper

    private val dataMovies = MutableLiveData<ArrayList<DataModel>>()
    private val dataTvs = MutableLiveData<ArrayList<DataModel>>()

    fun getMoviesInLocal(): MutableLiveData<ArrayList<DataModel>>? = runBlocking {
        val deferredMovie = async(Dispatchers.IO) {
            val cursor = movieHelper.getAllFavorites()
            MappingHelper.mapAllMoviesCursorToArray(cursor)
        }

        val movies = deferredMovie.await()
       dataMovies.postValue(movies)
        dataMovies
    }

    fun getTvShowsInLocal(): MutableLiveData<ArrayList<DataModel>>? = runBlocking {
        val deferredMovie = async(Dispatchers.IO) {
            val cursor = tvHelper.getAllFavorites()
            MappingHelper.mapAllTvsCursorToArray(cursor)
        }

        dataTvs.postValue(deferredMovie.await())
        dataTvs
    }
}
