package com.challenge.movies.moviemanager.presentation

data class MovieModel(
    val id: Long,
    val title: String,
    val poster: String,
    val overview: String,
    val releaseDate: String
)