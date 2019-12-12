package chaerul.project.movieapp.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import chaerul.project.movieapp.database.DatabaseContract.MovieColumns.Companion.TABLE_NAME
import chaerul.project.movieapp.database.DatabaseContract.MovieColumns.Companion._ID

class MovieHelper(context: Context) {

    companion object {
        private const val DATABASE_TABLE = TABLE_NAME
        private lateinit var databaseHelper: DatabaseHelper
        private var INSTANCE: MovieHelper? = null

        private lateinit var database: SQLiteDatabase

        fun getInstance(context: Context): MovieHelper {
            if (INSTANCE == null) {
                synchronized(SQLiteOpenHelper::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = MovieHelper(context)
                    }
                }
            }
            return INSTANCE as MovieHelper
        }
    }

    init {
        databaseHelper = DatabaseHelper(context)
    }

    @Throws(SQLiteException::class)
    fun open() {
        database = databaseHelper.readableDatabase
    }

    fun close() {
        databaseHelper.close()

        if (database.isOpen) {
            database.close()
        }
    }

    fun getAllFavorites(): Cursor{
        return database.query(
            DATABASE_TABLE,
            null,
            null,
            null,
            null,
            null,
            "$_ID ASC"
        )
    }

    fun getMovieFavoriteById(id: String): Cursor {
        return database.query(
            DATABASE_TABLE,
            null,
            "$_ID = ? and favorite = 1",
            arrayOf(id),
            null,
            null,
            null,
            null
        )
    }

    fun insertMovie(values: ContentValues?) : Long {
        return database.insert(DATABASE_TABLE, null, values)
    }

    fun updateMovie(id: String, values: ContentValues?): Int {
        return database.update(DATABASE_TABLE, values, "$_ID = '$id", null)
    }

    fun deleteById(id: String) : Int {
        return database.delete(DATABASE_TABLE, "$_ID = '$id", null)
    }
}