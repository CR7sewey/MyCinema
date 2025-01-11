package com.example.mycinema.list.dependencyInjection

import android.app.Application
import androidx.room.Room
import com.example.mycinema.common.data.local.MovieDao
import com.example.mycinema.common.data.local.MyCinemaDatabase
import com.example.mycinema.common.data.remote.RetroFitClient
import com.example.mycinema.dependencyInjection.DispatcherIO
import com.example.mycinema.list.data.ListRepository
import com.example.mycinema.list.data.MovieListRepository
import com.example.mycinema.list.data.local.LocalDataSource
import com.example.mycinema.list.data.local.MovieListLocalDataSource
import com.example.mycinema.list.data.remote.ListService
import com.example.mycinema.list.data.remote.MovieListRemoteDataSource
import com.example.mycinema.list.data.remote.RemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit

@Module // criar container
@InstallIn(ViewModelComponent::class)
class MovieListModule {


    @Provides
    fun providesListService(retrofit: Retrofit): ListService {
        return retrofit.create(ListService::class.java)
    }

}

@Module // criar container
@InstallIn(ViewModelComponent::class)
interface MovieListModuleBinding {

    @Binds
    fun bindListRepository(impl: MovieListRepository): ListRepository

    @Binds
    fun bindLocalDataSource(impl: MovieListLocalDataSource): LocalDataSource

    @Binds
    fun bindRemoteDataSource(impl: MovieListRemoteDataSource): RemoteDataSource

}