package com.example.billeasytest.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.billeasytest.model.Result

@Database(entities = [Result::class],
version = 1,
exportSchema = false)
abstract class MoviesDatabase : RoomDatabase()  {


    abstract fun reposDao(): MovieDao

    companion object {

        @Volatile
        private var INSTANCE: MoviesDatabase? = null

        fun getInstance(context: Context): MoviesDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                MoviesDatabase::class.java, "movies.db")
                .build()
    }
}

