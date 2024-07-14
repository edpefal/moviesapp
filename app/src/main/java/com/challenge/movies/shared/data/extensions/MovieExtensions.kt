package com.challenge.movies.shared.data.extensions

import com.challenge.movies.popular.data.PopularMovie
import com.challenge.movies.popular.presentation.PopularMovieModel

fun PopularMovie.toPopularMovieModel(): PopularMovieModel {
    return PopularMovieModel(
        id = this.id ?: 0,
        title = title.orEmpty(),
        poster = posterPath.orEmpty(),
        overview = overview.orEmpty(),
        releaseDate = releaseDate.orEmpty()
    )
}