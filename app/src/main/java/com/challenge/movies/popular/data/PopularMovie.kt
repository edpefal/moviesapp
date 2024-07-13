package com.challenge.movies.popular.data

data class PopularMovie(
    val adult: Boolean? = null,
    val backdropPath: String? = null,
    val genreIDS: List<Long>? = null,
    val id: Long? = null,
    val originalLanguage: String? = null,
    val originalTitle: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val posterPath: String? = null,
    val releaseDate: String? = null,
    val title: String? = null,
    val video: Boolean? = null,
    val voteAverage: Double? = null,
    val voteCount: Long? = null
)