package com.challenge.moviesmanager.data.db.di

import android.content.Context
import androidx.room.Room
import com.challenge.moviesmanager.data.db.MoviesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

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