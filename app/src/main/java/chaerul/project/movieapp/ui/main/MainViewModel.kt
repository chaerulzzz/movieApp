package chaerul.project.movieapp.ui.main

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import chaerul.project.movieapp.DataModel
import chaerul.project.movieapp.R

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private var dataMovie: ArrayList<DataModel> = arrayListOf()
    private var dataTvs: ArrayList<DataModel> = arrayListOf()

    override fun onCleared() {
        super.onCleared()
        dataMovie.clear()
        dataTvs.clear()
    }

    @SuppressLint("Recycle")
    fun getAllMovies(): ArrayList<DataModel> {
        dataMovie.clear()

        val dataName =
            getApplication<Application>().resources.getStringArray(R.array.data_movie_name)
        val dataDescription =
            getApplication<Application>().resources.getStringArray(R.array.data_movie_description)
        val dataPhoto = getApplication<Application>().resources.obtainTypedArray(R.array.data_photo)

        for (position in dataName.indices) {
            val hero = DataModel(
                dataPhoto.getResourceId(position, -1),
                dataName[position],
                dataDescription[position]
            )
            dataMovie.add(hero)
        }

        return dataMovie
    }

    @SuppressLint("Recycle")
    fun getAllTvShows(): ArrayList<DataModel> {
        dataTvs.clear()

        val dataName =
            getApplication<Application>().resources.getStringArray(R.array.data_tv_show_names)
        val dataDescription =
            getApplication<Application>().resources.getStringArray(R.array.data_tv_show_description)
        val dataPhoto =
            getApplication<Application>().resources.obtainTypedArray(R.array.data_tv_show_photos)

        for (position in dataName.indices) {
            val hero = DataModel(
                dataPhoto.getResourceId(position, -1),
                dataName[position],
                dataDescription[position]
            )
            dataTvs.add(hero)
        }

        return dataTvs
    }
}