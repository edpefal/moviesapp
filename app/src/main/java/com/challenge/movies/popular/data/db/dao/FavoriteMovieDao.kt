package com.challenge.movies.popular.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.challenge.movies.popular.data.db.entities.FavoriteMovieEntity


@Dao
interface FavoriteMovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(movie: FavoriteMovieEntity)

    @Query("SELECT * FROM favorite_movies")
    suspend fun getAllFavoriteMovies(): List<FavoriteMovieEntity>

    @Query("DELETE FROM favorite_movies WHERE id = :movieId")
    suspend fun deleteFavoriteMovieById(movieId: Long)


}