package chaerul.project.movieapp.api.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataModel(
    var id: Int? = null,
    var photo: String? = null,
    var title: String? = null,
    var overview: String? = null,
    var releaseDate: String? = null
): Parcelable