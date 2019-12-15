package chaerul.project.movieapp

import android.app.Application
import chaerul.project.movieapp.database.MovieHelper
import chaerul.project.movieapp.database.TVHelper

class MovieApplication : Application() {
    companion object{
        lateinit var movieHelper: MovieHelper
        lateinit var tvHelper: TVHelper
    }

    override fun onCreate() {
        super.onCreate()

        movieHelper = MovieHelper.getInstance(context = this)
        movieHelper.open()

        tvHelper = TVHelper.getInstance(context = this)
        tvHelper.open()
    }
}