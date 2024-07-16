package com.challenge.moviesmanager.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.challenge.moviesmanager.data.db.converters.Converters
import com.challenge.moviesmanager.data.db.dao.FavoriteMovieDao
import com.challenge.moviesmanager.data.db.entities.FavoriteMovieEntity

@Database(entities = [FavoriteMovieEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class MoviesDatabase: RoomDatabase() {
    abstract val favoriteMovieDao: FavoriteMovieDao

}