package com.challenge.movies.moviedetail.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.challenge.movies.R

@Composable
fun MovieDetailScreen(movieId: Long, movieDetailViewModel: MovieDetailViewModel) {

    LaunchedEffect(Unit) {
        movieDetailViewModel.getMovieDetail(movieId)
    }

    val movieDetailUiState by movieDetailViewModel.movieDetailUiState.collectAsState()

    when (movieDetailUiState) {
        MovieDetailUiState.Empty -> {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                Text("Movie not found.")
            }
        }

        MovieDetailUiState.Error -> {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                Text("There was an error loading the movie.")
            }
        }

        MovieDetailUiState.Loading -> {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator()
            }
        }

        is MovieDetailUiState.Success -> {
            val movie = (movieDetailUiState as MovieDetailUiState.Success).movie
            Scaffold(
                topBar = { MovieDetailTopAppBar() },
                floatingActionButton = {
                    SaveMovieFAB {
                        movieDetailViewModel.saveFavoriteMovie(movie)
                    }
                }) {
                Box(modifier = Modifier.padding(it).fillMaxSize()) {
                    AsyncImage(
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier.fillMaxSize(),
                        model = "https://image.tmdb.org/t/p/w500/${movie.poster}",
                        contentDescription = "Movie Poster",
                    )
                }
            }
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailTopAppBar() {
    TopAppBar(title = { Text("Movie Detail") })
}

@Composable
fun SaveMovieFAB(onClickAddMessage: () -> Unit) {
    FloatingActionButton(
        onClick = onClickAddMessage,
        containerColor = MaterialTheme.colorScheme.tertiary
    ) {
        Icon(
            Icons.Default.Favorite,
            contentDescription = stringResource(id = R.string.save_movie),
            tint = Color.White
        )
    }
}