package com.challenge.shared.data.routes

sealed class Routes(val route: String) {
    data object PopularMovies : Routes("popular")
    data object NowPlaying : Routes("playing")
    data object Favorites : Routes("favorites")
    data object MovieDetail : Routes("movie_detail/{movieId}") {
        fun createRoute(movieId: String) = "movie_detail/$movieId"

    }


}