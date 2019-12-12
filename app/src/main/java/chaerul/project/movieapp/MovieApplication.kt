package chaerul.project.movieapp

import android.app.Application
import chaerul.project.movieapp.database.MovieHelper

class MovieApplication : Application() {
    companion object{
        lateinit var movieHelper: MovieHelper
    }

    override fun onCreate() {
        super.onCreate()

        movieHelper = MovieHelper(context = this)
        movieHelper.open()
    }
}