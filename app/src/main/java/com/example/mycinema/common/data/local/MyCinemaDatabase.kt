package com.example.mycinema.common.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MovieEntity::class], version = 2)
abstract class MyCinemaDatabase: RoomDatabase() {
    abstract fun getMovieDao(): MovieDao

}