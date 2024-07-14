package com.challenge.movies.popular.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_movies")
data class FavoriteMovieEntity(
    @field:ColumnInfo(name = "id") @PrimaryKey val id: Long,
    @field:ColumnInfo(name = "title") val title: String,
    @field:ColumnInfo(name = "overview") val overview: String,
    @field:ColumnInfo(name = "posterPath") val posterPath: String,
    @field:ColumnInfo(name = "releaseDate") val releaseDate: String
)