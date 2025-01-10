package com.example.mycinema

import android.app.Application
import androidx.room.Room
import com.example.mycinema.common.data.remote.RetroFitClient
import com.example.mycinema.common.data.local.MyCinemaDatabase
import com.example.mycinema.list.data.MovieListRepository
import com.example.mycinema.list.data.local.MovieListLocalDataSource
import com.example.mycinema.list.data.remote.ListService
import com.example.mycinema.list.data.remote.MovieListRemoteDataSource
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyCinemaApplication : Application() {

    /*private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            MyCinemaDatabase::class.java, "cinema-database"
        ).build()
    }

    private val listService by lazy {
        RetroFitClient.retrofit.create(ListService::class.java)
    }
    private val localDataSource: MovieListLocalDataSource by lazy {
        MovieListLocalDataSource(db.getMovieDao())
    }

    private val remoteDataSource: MovieListRemoteDataSource by lazy {
        MovieListRemoteDataSource(listService)
    }

    val repository: MovieListRepository by lazy {
       MovieListRepository(movieListRemoteDataSource= remoteDataSource, movieListLocalDataSource = localDataSource)
    }*/

   /* val repository: MovieListRepository by lazy {
        MyCinemaServiceLocator.getRepository(application = this)
    }*/



}