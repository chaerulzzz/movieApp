package chaerul.project.movieapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import chaerul.project.movieapp.api.model.DataModel

class DetailViewModel : ViewModel() {

    private var data = MutableLiveData<DataModel>()

    fun getDataModel(): LiveData<DataModel> {
        return data
    }

    fun setDataModel(dataModel: DataModel) {
        this.data.postValue(dataModel)
    }
}