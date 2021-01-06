package com.example.billeasytest.db

import androidx.paging.PagingSource
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.billeasytest.model.Result

interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(repo: List<Result>)

    @Query("select * from movies")
    fun reposByName(): PagingSource<Int, Result>

    @Query("DELETE FROM movies")
    suspend fun clearRepos()
}