package chaerul.project.movieapp.ui.detail

import android.app.Application
import android.content.ContentValues
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import chaerul.project.movieapp.MovieApplication
import chaerul.project.movieapp.api.model.DataModel
import chaerul.project.movieapp.database.DatabaseContract
import chaerul.project.movieapp.database.MovieHelper
import chaerul.project.movieapp.helper.MappingHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class DetailViewModel : AndroidViewModel(Application()) {

    private var data = MutableLiveData<DataModel>()
    private var movieHelper: MovieHelper = MovieApplication.movieHelper

    fun getDataModel(): LiveData<DataModel> {
        return data
    }

    fun setDataModel(dataModel: DataModel) {
        this.data.postValue(dataModel)
    }

    fun addFavoriteMovie(dataModel: DataModel) {
        val contentValues = ContentValues()
        contentValues.put(DatabaseContract.MovieColumns._ID, dataModel.id)
        contentValues.put(DatabaseContract.MovieColumns.PHOTO, dataModel.photo)
        contentValues.put(DatabaseContract.MovieColumns.TITLE, dataModel.title)
        contentValues.put(DatabaseContract.MovieColumns.RELEASE_DATE, dataModel.releaseDate)
        contentValues.put(DatabaseContract.MovieColumns.OVERVIEW, dataModel.overview)
        contentValues.put(DatabaseContract.MovieColumns.FAVORITE, 1)
        movieHelper.insertMovie(contentValues)
    }

    fun getDataInLocal(id: Int): DataModel? = runBlocking {
        val deferredMovie = async(Dispatchers.IO) {
            val cursor = movieHelper.getMovieFavoriteById(id.toString())
            MappingHelper.mapMovieCursorToArray(cursor)
        }

        val movie: DataModel? = deferredMovie.await()
        movie
    }
}