package com.example.mycinema.detail.dependencyInjection

import com.example.mycinema.detail.data.DetailService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module // criar container
@InstallIn(ViewModelComponent::class)
class MovieDetailsModule {
    @Provides
    fun providesMovieDetailsService(retrofit: Retrofit): DetailService {
        return retrofit.create(DetailService::class.java)
    }
}