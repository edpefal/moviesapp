package com.challenge.movies.popular.presentation

data class PopularMovieModel(
    val id: Long,
    val title: String,
    val poster: String,
    val overview: String,
    val releaseDate: String
)