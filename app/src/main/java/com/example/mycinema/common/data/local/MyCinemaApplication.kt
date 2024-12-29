package com.example.mycinema.common.data.local

import android.app.Application
import androidx.room.Room

class MyCinemaApplication : Application() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            MyCinemaDatabase::class.java, "mycinema-database"
        ).build()
    }

    private val getMovieDao by lazy {
        db.getMovieDao()
    }



    private lateinit var moviesFromDB: List<MovieEntity>

    override fun onCreate() {
        super.onCreate()
    }



}