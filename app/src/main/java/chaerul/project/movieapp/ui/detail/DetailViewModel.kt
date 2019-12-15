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
import chaerul.project.movieapp.database.TVHelper
import chaerul.project.movieapp.helper.MappingHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class DetailViewModel : AndroidViewModel(Application()) {

    private var isMovie: Boolean = false
    private var data = MutableLiveData<DataModel>()
    private var movieHelper: MovieHelper = MovieApplication.movieHelper
    private var tvHelper: TVHelper = MovieApplication.tvHelper

    fun getDataModel(): LiveData<DataModel> {
        return data
    }

    fun setDataModel(dataModel: DataModel) {
        this.data.postValue(dataModel)
    }

    fun setThisDataIsMovie(condition: Boolean) {
        isMovie = condition
    }

    fun getThisDataIsMovie() : Boolean = isMovie

    fun addFavoriteData(dataModel: DataModel) {
        val contentValues = ContentValues()

        if (isMovie) {
            contentValues.put(DatabaseContract.MovieColumns._ID, dataModel.id)
            contentValues.put(DatabaseContract.MovieColumns.PHOTO, dataModel.photo)
            contentValues.put(DatabaseContract.MovieColumns.TITLE, dataModel.title)
            contentValues.put(DatabaseContract.MovieColumns.RELEASE_DATE, dataModel.releaseDate)
            contentValues.put(DatabaseContract.MovieColumns.OVERVIEW, dataModel.overview)
            contentValues.put(DatabaseContract.MovieColumns.FAVORITE, 1)
            movieHelper.insertMovie(contentValues)
        } else {
            contentValues.put(DatabaseContract.TVShowsColumns._ID, dataModel.id)
            contentValues.put(DatabaseContract.TVShowsColumns.PHOTO, dataModel.photo)
            contentValues.put(DatabaseContract.TVShowsColumns.NAME, dataModel.title)
            contentValues.put(DatabaseContract.TVShowsColumns.FIRST_AIR_DATE, dataModel.releaseDate)
            contentValues.put(DatabaseContract.TVShowsColumns.OVERVIEW, dataModel.overview)
            contentValues.put(DatabaseContract.TVShowsColumns.FAVORITE, 1)
            tvHelper.insertTvShow(contentValues)
        }
    }

    fun deleteFavoriteData(id: Int){
        if (isMovie) {
            movieHelper.deleteById(id.toString())
        } else {
            tvHelper.deleteById(id.toString())
        }
    }

    fun getDataInLocal(id: Int): DataModel? = runBlocking {
        val deferredMovie = async(Dispatchers.IO) {
            if (isMovie) {
                val cursor = movieHelper.getMovieFavoriteById(id.toString())
                MappingHelper.mapMovieCursorToArray(cursor)
            } else {
                val cursor = tvHelper.getTvShowFavoriteById(id.toString())
                MappingHelper.mapTvCursorToArray(cursor)
            }
        }

        val data: DataModel? = deferredMovie.await()
        data
    }
}