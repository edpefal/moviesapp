package com.challenge.movies.shared.data.extensions

import com.challenge.movies.moviemanager.data.Movie
import com.challenge.movies.moviemanager.presentation.MovieModel

fun Movie.toPopularMovieModel(): MovieModel {
    return MovieModel(
        id = this.id ?: 0,
        title = title.orEmpty(),
        poster = posterPath.orEmpty(),
        overview = overview.orEmpty(),
        releaseDate = releaseDate.orEmpty()
    )
}