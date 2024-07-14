package com.challenge.movies.popular.data.db.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.challenge.movies.popular.data.PopularMoviesClient
import com.challenge.movies.popular.data.MoviesRepositoryImpl
import com.challenge.movies.popular.data.db.MoviesDatabase
import com.challenge.movies.popular.domain.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    fun provideMovieDatabase(@ApplicationContext context: Context): MoviesDatabase {
        return Room.databaseBuilder(
            context,
            MoviesDatabase::class.java,
            "MOVIES_DB"
        ).build()
    }

    @Provides
    fun provideFavoriteMoviesDao(moviesDatabase: MoviesDatabase) = moviesDatabase.favoriteMovieDao
}