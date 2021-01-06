package com.example.billeasytest.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.billeasytest.model.Result
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(repo: List<Result>)

    @Query("Select * from movies ORDER BY releaseDate DESC")
     fun getAllMovies() :Flow<List<Result>>

    @Query("DELETE FROM movies")
    suspend fun clearRepos()
}