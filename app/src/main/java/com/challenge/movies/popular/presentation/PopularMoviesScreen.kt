package com.challenge.movies.popular.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.challenge.movies.shared.routes.Routes

@Composable
fun PopularMoviesScreen(
    popularMoviesViewModel: PopularMoviesViewModel,
    navController: NavHostController
) {
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
                LazyColumn() {
                    items(movies) { movie ->
                        MovieItem(movie, onCardClick = {
                            navController.navigate(Routes.MovieDetail.createRoute("${movie.id}"))
                        })
                    }
                }
            }
        }

        PopularMoviesUiState.Empty -> {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                Text("There are no movies to display.")
            }

        }

        PopularMoviesUiState.Error -> {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                Text("There was an error loading movies.")
            }
        }

    }
}

@Composable
fun MovieItem(movie: PopularMovieModel, onCardClick: () -> Unit) {
    Card(modifier = Modifier.clickable { onCardClick() }) {
        Row(modifier = Modifier.height(180.dp)) {
            AsyncImage(
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    //.weight(2f)
                    .width(100.dp),
                //.height(250.dp),
                //.fillMaxSize(),
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


    //Text(text = movie.title)
}
