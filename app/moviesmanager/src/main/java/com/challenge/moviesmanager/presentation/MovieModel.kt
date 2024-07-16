package com.challenge.moviesmanager.presentation

data class MovieModel(
    val id: Long,
    val title: String,
    val poster: String,
    val overview: String,
    val releaseDate: String,
    val voteAverage: Double,
    val popularity: Double,
    val languages: String
)