package com.challenge.movies.popular.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun PopularMoviesScreen(popularMoviesViewModel: PopularMoviesViewModel) {
    val popularMovies by popularMoviesViewModel.popularMoviesUiState.collectAsState()
    LaunchedEffect(Unit) {
        popularMoviesViewModel.getPopularMovies(1)
    }
    when (popularMovies) {
        is PopularMoviesUiState.Loading -> {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator()
            }
        }

        is PopularMoviesUiState.Success -> {
            val movies = (popularMovies as PopularMoviesUiState.Success).movies
            Column {
                Text(text = "Popular Movies", style = MaterialTheme.typography.headlineLarge)
                LazyColumn {
                    items(movies) { movie ->
                        MovieItem(movie)
                    }
                }
            }
        }

        PopularMoviesUiState.Empty -> {


        }

        PopularMoviesUiState.Error -> {

        }

    }
}

@Composable
fun MovieItem(movie: PopularMovieModel) {
    Text(text = movie.title)
}
