package com.challenge.movies.shared.data.extensions

import com.challenge.moviesmanager.data.Movie
import com.challenge.moviesmanager.presentation.MovieModel

fun Movie.toPopularMovieModel(): MovieModel {
    return MovieModel(
        id = this.id ?: 0,
        title = title.orEmpty(),
        poster = posterPath.orEmpty(),
        overview = overview.orEmpty(),
        releaseDate = releaseDate.orEmpty(),
        popularity = popularity ?: 0.0,
        voteAverage = voteAverage ?: 0.0,
        languages = originalLanguage.orEmpty(),
    )
}