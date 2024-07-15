package com.challenge.movies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.challenge.movies.favorites.presentation.FavoriteMoviesViewModel
import com.challenge.movies.moviedetail.presentation.MovieDetailViewModel
import com.challenge.movies.popular.presentation.PopularMoviesViewModel
import com.challenge.movies.shared.presentation.screens.MainMoviesScreen
import com.challenge.movies.ui.theme.MoviesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val popularMoviesViewModel: PopularMoviesViewModel by viewModels()
    private val movieDetailViewModel: MovieDetailViewModel by viewModels()
    private val favoriteMoviesViewModel: FavoriteMoviesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainMoviesScreen(
                popularMoviesViewModel = popularMoviesViewModel,
                movieDetailViewModel = movieDetailViewModel,
                favoriteMoviesViewModel = favoriteMoviesViewModel
            )
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MoviesTheme {
        Greeting("Android")
    }
}