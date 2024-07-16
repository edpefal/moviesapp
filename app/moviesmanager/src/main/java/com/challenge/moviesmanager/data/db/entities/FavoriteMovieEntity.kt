package com.challenge.moviesmanager.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_movies")
data class FavoriteMovieEntity(
    @field:ColumnInfo(name = "id") @PrimaryKey val id: Long,
    @field:ColumnInfo(name = "title") val title: String,
    @field:ColumnInfo(name = "overview") val overview: String,
    @field:ColumnInfo(name = "posterPath") val posterPath: String,
    @field:ColumnInfo(name = "releaseDate") val releaseDate: String,
    @field:ColumnInfo(name = "voteAverage") val voteAverage: Double,
    @field:ColumnInfo(name = "popularity") val popularity: Double,
    @field:ColumnInfo(name = "languages") val languages: String
)