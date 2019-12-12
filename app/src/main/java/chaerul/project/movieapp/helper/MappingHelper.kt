package chaerul.project.movieapp.helper

import android.database.Cursor
import chaerul.project.movieapp.api.model.DataModel
import chaerul.project.movieapp.database.DatabaseContract

object MappingHelper {
    fun mapAllMoviesCursorToArray(movieCursor: Cursor): ArrayList<DataModel> {
        val notesList = ArrayList<DataModel>()
        movieCursor.moveToFirst()
        while (movieCursor.moveToNext()) {
            val id = movieCursor.getInt(movieCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns._ID))
            val title = movieCursor.getString(movieCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.TITLE))
            val description = movieCursor.getString(movieCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.OVERVIEW))
            val photo = movieCursor.getString(movieCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.PHOTO))
            val date = movieCursor.getString(movieCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.RELEASE_DATE))
            notesList.add(DataModel(id,  photo, title, description, date))
        }

        return notesList
    }

    fun mapMovieCursorToArray(movieCursor: Cursor): DataModel? {
        movieCursor.moveToFirst()

        if (movieCursor.count > 0) {
            val id =
                movieCursor.getInt(movieCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns._ID))
            val title =
                movieCursor.getString(movieCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.TITLE))
            val description =
                movieCursor.getString(movieCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.OVERVIEW))
            val photo =
                movieCursor.getString(movieCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.PHOTO))
            val date =
                movieCursor.getString(movieCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.RELEASE_DATE))
            val model = DataModel(id, photo, title, description, date)

            return model
        } else {
            return null
        }
    }
}