package chaerul.project.movieapp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieModel(var photo: Int, var name: String, var descrption: String): Parcelable