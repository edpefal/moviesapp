package com.challenge.movies.shared.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.challenge.movies.moviemanager.presentation.MovieModel
import com.challenge.movies.shared.presentation.models.MoviesUiState
import com.challenge.movies.shared.presentation.viewmodel.MoviesViewModel
import com.challenge.movies.shared.routes.Routes

@Composable
fun MoviesListScreen(
    title: String,
    moviesViewModel: MoviesViewModel,
    navController: NavHostController
) {
    val moviesUiState by moviesViewModel.moviesUiState.collectAsState()
    val lazyListState = rememberLazyListState()

    LaunchedEffect(Unit) {
        moviesViewModel.getMovies()
    }
    when (moviesUiState) {
        is MoviesUiState.Loading -> {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator()
            }
        }

        is MoviesUiState.Success -> {
            val movies = (moviesUiState as MoviesUiState.Success).movies
            Column {
                Text(text = title, style = MaterialTheme.typography.headlineLarge)
                LazyColumn(state = lazyListState) {
                    items(movies) { movie ->
                        MovieItem(movie, onCardClick = {
                            navController.navigate(Routes.MovieDetail.createRoute("${movie.id}"))
                        })
                    }
                }

                LaunchedEffect(lazyListState) {
                    snapshotFlow { lazyListState.layoutInfo.visibleItemsInfo }
                        .collect { visibleItems ->
                            val lastVisibleItemIndex = visibleItems.lastOrNull()?.index ?: 0
                            if (lastVisibleItemIndex == movies.size - 1) {
                                moviesViewModel.getMovies()
                            }
                        }
                }
            }
        }

        MoviesUiState.Empty -> {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                Text("There are no movies to display.")
            }

        }

        MoviesUiState.Error -> {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                Text("There was an error loading movies.")
            }
        }

    }
}

@Composable
fun MovieItem(movie: MovieModel, onCardClick: () -> Unit) {
    Card(modifier = Modifier.clickable { onCardClick() }) {
        Row(modifier = Modifier.height(180.dp)) {
            AsyncImage(
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .width(100.dp),
                model = "https://image.tmdb.org/t/p/w500/${movie.poster}",
                contentDescription = "Movie Poster",
            )
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.headlineSmall,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = movie.releaseDate,
                    style = MaterialTheme.typography.titleMedium,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = movie.overview,
                    style = MaterialTheme.typography.bodyMedium,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 4
                )
            }

        }
    }

}
