package com.challenge.movies.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.challenge.movies.R
import com.challenge.moviesmanager.presentation.MovieModel
import com.challenge.shared.presentation.models.MoviesUiState
import com.challenge.shared.presentation.viewmodel.MoviesViewModel
import com.challenge.shared.data.routes.Routes

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
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = title, style = MaterialTheme.typography.displaySmall, modifier = Modifier.padding(bottom = 16.dp))
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
                Text(stringResource(id = R.string.no_movies))
            }

        }


        MoviesUiState.Error -> {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                Text(stringResource(id = R.string.movies_error))
            }
        }

    }
}

@Composable
fun MovieItem(movie: MovieModel, onCardClick: () -> Unit) {
    Card(elevation = CardDefaults.cardElevation(defaultElevation = 4.dp), modifier = Modifier
        .padding(bottom = 16.dp)
        .clickable { onCardClick() }) {
        Row(modifier = Modifier.height(180.dp)) {
            AsyncImage(
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .width(100.dp),
                model = stringResource(id = R.string.images_path, movie.poster),
                contentDescription = "Movie Poster",
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
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
