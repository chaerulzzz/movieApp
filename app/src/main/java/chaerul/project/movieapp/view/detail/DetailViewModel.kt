package chaerul.project.movieapp.view.detail

import chaerul.project.movieapp.MovieModel

class DetailViewModel(private val movie: MovieModel?) {

    fun getPhoto() = movie?.photo

    fun getName() = movie?.name

    fun getDescription() = movie?.descrption
}