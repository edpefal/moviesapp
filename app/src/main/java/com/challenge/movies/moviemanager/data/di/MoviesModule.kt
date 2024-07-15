package com.challenge.movies.moviemanager.data.di

import com.challenge.movies.moviemanager.data.MoviesClient
import com.challenge.movies.moviemanager.data.MoviesRepositoryImpl
import com.challenge.movies.moviemanager.domain.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class MoviesModule {

    @Provides
    fun provideMoviesClient(retrofit: Retrofit): MoviesClient {
        return retrofit.create(MoviesClient::class.java)
    }

    @Provides
    fun provideMoviesRepository(moviesRepositoryImpl: MoviesRepositoryImpl): MoviesRepository {
        return moviesRepositoryImpl
    }
}