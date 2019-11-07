package chaerul.project.movieapp.view.main

import android.annotation.SuppressLint
import android.content.Context
import chaerul.project.movieapp.MovieModel
import chaerul.project.movieapp.R
import java.util.ArrayList

class MainViewModel(private val context: Context) {

    @SuppressLint("Recycle")
    fun getAllData(): ArrayList<MovieModel> {
        var movies: ArrayList<MovieModel> = arrayListOf()
        val dataName = context.resources.getStringArray(R.array.data_name)
        val dataDescription = context.resources.getStringArray(R.array.data_description)
        val dataPhoto = context.resources.obtainTypedArray(R.array.data_photo)

        for (position in dataName.indices) {
            val hero = MovieModel(
                dataPhoto.getResourceId(position, -1),
                dataName[position],
                dataDescription[position]
            )
            movies.add(hero)
        }

        return movies
    }
}