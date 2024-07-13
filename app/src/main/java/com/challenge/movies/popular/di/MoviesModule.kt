package com.challenge.movies.popular.di

import com.challenge.movies.popular.data.PopularMoviesClient
import com.challenge.movies.popular.data.PopularMoviesRepositoryImpl
import com.challenge.movies.popular.domain.PopularMoviesRepository
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
    fun provideMoviesRepository(popularMoviesRepositoryImpl: PopularMoviesRepositoryImpl): PopularMoviesRepository {
        return popularMoviesRepositoryImpl
    }
}