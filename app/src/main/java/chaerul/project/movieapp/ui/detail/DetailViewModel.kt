package chaerul.project.movieapp.ui.detail

import androidx.lifecycle.ViewModel
import chaerul.project.movieapp.DataModel

class DetailViewModel : ViewModel() {

    private lateinit var data: DataModel

    fun getPhoto() = data.photo

    fun getName() = data.name

    fun getDescription() = data.descrption

    fun setMovieModel(dataModel: DataModel) {
        this.data = dataModel
    }
}