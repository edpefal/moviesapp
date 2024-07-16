package com.challenge.moviesmanager.presentation

data class MovieListModel(
    val results: List<MovieModel>,
    val totalPages: Int,
)