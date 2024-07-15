package com.challenge.movies.moviemanager.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.challenge.movies.moviemanager.data.db.dao.FavoriteMovieDao
import com.challenge.movies.moviemanager.data.db.entities.FavoriteMovieEntity

@Database(entities = [FavoriteMovieEntity::class], version = 1)
abstract class MoviesDatabase: RoomDatabase() {
    abstract val favoriteMovieDao: FavoriteMovieDao

}