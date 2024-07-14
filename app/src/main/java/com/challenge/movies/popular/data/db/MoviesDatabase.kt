package com.challenge.movies.popular.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.challenge.movies.popular.data.db.dao.FavoriteMovieDao
import com.challenge.movies.popular.data.db.entities.FavoriteMovieEntity

@Database(entities = [FavoriteMovieEntity::class], version = 1)
abstract class MoviesDatabase: RoomDatabase() {
    abstract val favoriteMovieDao: FavoriteMovieDao

}