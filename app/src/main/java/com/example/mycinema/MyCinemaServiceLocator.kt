package com.example.mycinema

import android.app.Application
import androidx.room.Room
import com.example.mycinema.common.data.local.MyCinemaDatabase
import com.example.mycinema.common.data.remote.RetroFitClient
import com.example.mycinema.list.data.ListRepository
import com.example.mycinema.list.data.MovieListRepository
import com.example.mycinema.list.data.local.LocalDataSource
import com.example.mycinema.list.data.local.MovieListLocalDataSource
import com.example.mycinema.list.data.remote.ListService
import com.example.mycinema.list.data.remote.MovieListRemoteDataSource
import com.example.mycinema.list.data.remote.RemoteDataSource

object MyCinemaServiceLocator {



    fun getRepository(application: Application): MovieListRepository {
        val db by lazy {
            Room.databaseBuilder(
                application.applicationContext,
                MyCinemaDatabase::class.java, "cinema-database"
            ).build()
        }

        val listService = RetroFitClient.retrofit.create(ListService::class.java)

        val localDataSource: LocalDataSource = MovieListLocalDataSource(db.getMovieDao())


        val remoteDataSource: RemoteDataSource = MovieListRemoteDataSource(listService)


        val repository: ListRepository = MovieListRepository(movieListRemoteDataSource= remoteDataSource, movieListLocalDataSource = localDataSource)

        return repository as MovieListRepository
    }


}