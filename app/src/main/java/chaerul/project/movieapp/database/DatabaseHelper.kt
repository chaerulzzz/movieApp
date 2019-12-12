package chaerul.project.movieapp.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import chaerul.project.movieapp.database.DatabaseContract.MovieColumns.Companion.TABLE_NAME

internal class DatabaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME =  "tmdbapp"
        private const val DATABASE_VERSION = 1
        private const val SQL_CREATE_TABLE_MOVIE = "CREATE TABLE $TABLE_NAME" +
                " (${DatabaseContract.MovieColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                " ${DatabaseContract.MovieColumns.TITLE} TEXT NOT NULL," +
                " ${DatabaseContract.MovieColumns.OVERVIEW} TEXT NOT NULL," +
                " ${DatabaseContract.MovieColumns.PHOTO} TEXT NOT NULL, " +
                " ${DatabaseContract.MovieColumns.FAVORITE} INTEGER NOT NULL, " +
                "${DatabaseContract.MovieColumns.RELEASE_DATE} TEXT NOT NULL)"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_TABLE_MOVIE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }


}