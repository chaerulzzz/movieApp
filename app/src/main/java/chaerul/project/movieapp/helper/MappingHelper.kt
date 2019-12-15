package chaerul.project.movieapp.helper

import android.database.Cursor
import chaerul.project.movieapp.api.model.DataModel
import chaerul.project.movieapp.database.DatabaseContract

object MappingHelper {
    fun mapAllMoviesCursorToArray(movieCursor: Cursor): ArrayList<DataModel> {
        val notesList = ArrayList<DataModel>()
        while (movieCursor.moveToNext()) {
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
            notesList.add(DataModel(id, photo, title, description, date))
        }

        return notesList
    }

    fun mapAllTvsCursorToArray(tvCursor: Cursor): ArrayList<DataModel> {
        val notesList = ArrayList<DataModel>()
        while (tvCursor.moveToNext()) {
            val id =
                tvCursor.getInt(tvCursor.getColumnIndexOrThrow(DatabaseContract.TVShowsColumns._ID))
            val title =
                tvCursor.getString(tvCursor.getColumnIndexOrThrow(DatabaseContract.TVShowsColumns.NAME))
            val description =
                tvCursor.getString(tvCursor.getColumnIndexOrThrow(DatabaseContract.TVShowsColumns.OVERVIEW))
            val photo =
                tvCursor.getString(tvCursor.getColumnIndexOrThrow(DatabaseContract.TVShowsColumns.PHOTO))
            val date =
                tvCursor.getString(tvCursor.getColumnIndexOrThrow(DatabaseContract.TVShowsColumns.FIRST_AIR_DATE))
            notesList.add(DataModel(id, photo, title, description, date))
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

            return DataModel(id, photo, title, description, date)
        } else {
            return null
        }
    }

    fun mapTvCursorToArray(tvCursor: Cursor): DataModel? {
        tvCursor.moveToFirst()

        if (tvCursor.count > 0) {
            val id =
                tvCursor.getInt(tvCursor.getColumnIndexOrThrow(DatabaseContract.TVShowsColumns._ID))
            val title =
                tvCursor.getString(tvCursor.getColumnIndexOrThrow(DatabaseContract.TVShowsColumns.NAME))
            val description =
                tvCursor.getString(tvCursor.getColumnIndexOrThrow(DatabaseContract.TVShowsColumns.OVERVIEW))
            val photo =
                tvCursor.getString(tvCursor.getColumnIndexOrThrow(DatabaseContract.TVShowsColumns.PHOTO))
            val date =
                tvCursor.getString(tvCursor.getColumnIndexOrThrow(DatabaseContract.TVShowsColumns.FIRST_AIR_DATE))

            return DataModel(id, photo, title, description, date)
        } else {
            return null
        }
    }
}