package com.challenge.movies.popular.presentation

data class MovieListModel(
    val results: List<PopularMovieModel>,
    val totalPages: Int,
)