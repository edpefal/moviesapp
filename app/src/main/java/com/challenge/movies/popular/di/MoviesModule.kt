package com.challenge.movies.popular.di

import com.challenge.movies.popular.data.PopularMoviesClient
import com.challenge.movies.popular.data.MoviesRepositoryImpl
import com.challenge.movies.popular.domain.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class MoviesModule {

    @Provides
    fun provideMoviesClient(retrofit: Retrofit): PopularMoviesClient {
        return retrofit.create(PopularMoviesClient::class.java)
    }

    @Provides
    fun provideMoviesRepository(moviesRepositoryImpl: MoviesRepositoryImpl): MoviesRepository {
        return moviesRepositoryImpl
    }
}