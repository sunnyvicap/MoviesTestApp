package com.example.billeasytest.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.billeasytest.model.Result

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(repo: List<Result>)

    @Query("DELETE FROM movies")
    suspend fun clearRepos()
}