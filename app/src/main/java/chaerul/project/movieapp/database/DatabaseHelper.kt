package chaerul.project.movieapp.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import chaerul.project.movieapp.database.DatabaseContract.MovieColumns
import chaerul.project.movieapp.database.DatabaseContract.TVShowsColumns

internal class DatabaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME =  "tmdbapp"
        private const val DATABASE_VERSION = 1
        private const val SQL_CREATE_TABLE_MOVIE = "CREATE TABLE ${MovieColumns.TABLE_NAME}" +
                " (${MovieColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                " ${MovieColumns.TITLE} TEXT NOT NULL," +
                " ${MovieColumns.OVERVIEW} TEXT NOT NULL," +
                " ${MovieColumns.PHOTO} TEXT NOT NULL, " +
                " ${MovieColumns.FAVORITE} INTEGER NOT NULL, " +
                "${MovieColumns.RELEASE_DATE} TEXT NOT NULL)"

        private const val SQL_CREATE_TABLE_TV = "CREATE TABLE ${TVShowsColumns.TABLE_NAME}" +
                " (${TVShowsColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                " ${TVShowsColumns.NAME} TEXT NOT NULL," +
                " ${TVShowsColumns.OVERVIEW} TEXT NOT NULL," +
                " ${TVShowsColumns.PHOTO} TEXT NOT NULL, " +
                " ${TVShowsColumns.FAVORITE} INTEGER NOT NULL, " +
                "${TVShowsColumns.FIRST_AIR_DATE} TEXT NOT NULL)"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_TABLE_MOVIE)
        db.execSQL(SQL_CREATE_TABLE_TV)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS ${MovieColumns.TABLE_NAME}")
        db.execSQL("DROP TABLE IF EXISTS ${TVShowsColumns.TABLE_NAME}")
        onCreate(db)
    }

}