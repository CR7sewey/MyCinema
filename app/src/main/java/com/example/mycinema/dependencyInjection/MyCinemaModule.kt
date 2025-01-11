package com.example.mycinema.dependencyInjection

import android.app.Application
import androidx.room.CoroutinesRoom
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mycinema.common.data.local.MovieDao
import com.example.mycinema.common.data.local.MyCinemaDatabase
import com.example.mycinema.common.data.remote.RetroFitClient
import com.example.mycinema.list.data.remote.ListService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit

// Migration dependencies
// dependencias ao nivel do escopo da app, que tem de ficar vivas enquanto app ficar viva, sao dependencias pesadas, instancia ativa

@Module // criar container
@InstallIn(SingletonComponent::class)
class MyCinemaModule {

    @Provides
    fun providesMyCinemaDB(application: Application): MyCinemaDatabase {
        return Room.databaseBuilder(
                application.applicationContext,
                MyCinemaDatabase::class.java, "cinema-database"
            ).build()

    }

    @Provides
    fun providesMovieDao(room: MyCinemaDatabase): MovieDao {
        return room.getMovieDao()
    }



    @Provides
    fun providesRetrofit(): Retrofit {
        return RetroFitClient.retrofit
    }



    @Provides
    @DispatcherIO // caso tenha outro dispatcher para distinguir
    fun providesDispatcherIO(): CoroutineDispatcher {
        return Dispatchers.IO
    }
}