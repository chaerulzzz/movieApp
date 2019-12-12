package chaerul.project.movieapp.database

import android.provider.BaseColumns

internal class DatabaseContract {

    internal class MovieColumns: BaseColumns {
        companion object{
            const val TABLE_NAME = "movie"
            const val _ID = "_id"
            const val TITLE = "title"
            const val OVERVIEW = "overview"
            const val PHOTO = "photo"
            const val RELEASE_DATE = "release_date"
            const val FAVORITE = "favorite"
        }
    }

    internal class TVShowsColumns: BaseColumns {
        companion object{
            const val TABLE_NAME = "tvshow"
            const val _ID = "_id"
            const val NAME = "name"
            const val OVERVIEW = "overview"
            const val PHOTO = "photo"
            const val FIRST_AIR_DATE = "first_air_date"
            const val FAVORITE = "favorite"
        }
    }
}