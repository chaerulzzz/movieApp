package chaerul.project.movieapp.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import chaerul.project.movieapp.database.DatabaseContract.TVShowsColumns.Companion.TABLE_NAME
import chaerul.project.movieapp.database.DatabaseContract.TVShowsColumns.Companion._ID

class TVHelper(context: Context) {
    companion object {
        private const val DATABASE_TABLE = TABLE_NAME
        private lateinit var databaseHelper: DatabaseHelper
        private var INSTANCE: TVHelper? = null

        private lateinit var database: SQLiteDatabase

        fun getInstance(context: Context): TVHelper {
            if (INSTANCE == null) {
                synchronized(SQLiteOpenHelper::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = TVHelper(context)
                    }
                }
            }
            return INSTANCE as TVHelper
        }
    }

    init {
        databaseHelper = DatabaseHelper(context)
    }

    @Throws(SQLiteException::class)
    fun open() {
        database = databaseHelper.readableDatabase
    }

    fun getAllFavorites(): Cursor {
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

    fun getTvShowFavoriteById(id: String): Cursor {
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

    fun insertTvShow(values: ContentValues?) : Long {
        return database.insert(DATABASE_TABLE, null, values)
    }

    fun deleteById(id: String) : Int {
        return database.delete(DATABASE_TABLE, "$_ID = $id", null)
    }
}