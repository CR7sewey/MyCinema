package com.example.mycinema.common.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(moviesList: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOne(vararg moviesList: MovieEntity)

    @Update
    suspend fun update(movieEntity: MovieEntity)

    @Delete
    suspend fun delete(movieEntity: MovieEntity)

    @Query("delete from movieentity where category is :categoryName")
    suspend fun deleteByCategoryName(categoryName: String)

    @Query("Select * From movieentity where category is :categoryName")
    suspend fun getAllByCategoryName(categoryName: String): List<MovieEntity>


}