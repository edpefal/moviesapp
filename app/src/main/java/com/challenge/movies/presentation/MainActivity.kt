package com.challenge.movies.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.challenge.favorites.presentation.FavoriteMoviesViewModel
import com.challenge.moviedetail.presentation.MovieDetailViewModel
import com.challenge.popular.presentation.PopularMoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val popularMoviesViewModel: PopularMoviesViewModel by viewModels()
    private val movieDetailViewModel: MovieDetailViewModel by viewModels()
    private val favoriteMoviesViewModel: FavoriteMoviesViewModel by viewModels()
    private val nowPlayingMoviesViewModel: com.challenge.nowplaying.presentation.NowPlayingMoviesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainMoviesScreen(
                popularMoviesViewModel = popularMoviesViewModel,
                movieDetailViewModel = movieDetailViewModel,
                favoriteMoviesViewModel = favoriteMoviesViewModel,
                nowPlayingMoviesViewModel = nowPlayingMoviesViewModel
            )
        }
    }
}
