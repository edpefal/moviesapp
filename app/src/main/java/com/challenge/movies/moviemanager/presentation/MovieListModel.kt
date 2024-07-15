package com.challenge.movies.moviemanager.presentation

data class MovieListModel(
    val results: List<MovieModel>,
    val totalPages: Int,
)