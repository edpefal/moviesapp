package com.challenge.movies.moviedetail.presentation

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.challenge.movies.R

@Composable
fun MovieDetailScreen(
    movieId: Long,
    movieDetailViewModel: MovieDetailViewModel,
    navController: NavHostController
) {

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
            val scrollState = rememberScrollState()
            val context = LocalContext.current

            Scaffold(
                topBar = { MovieDetailTopAppBar(movie.title) {
                    navController.popBackStack()
                }
                },
                floatingActionButton = {
                    SaveMovieFAB {
                        Toast.makeText(context, "Movie saved", Toast.LENGTH_SHORT).show()
                        movieDetailViewModel.saveFavoriteMovie(movie)
                    }
                }) {
                Column(
                    modifier = Modifier
                        .verticalScroll(scrollState)
                        .padding(it)
                ) {
                    AsyncImage(
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        model = stringResource(id = R.string.images_path, movie.poster),
                        contentDescription = "Movie Poster",
                    )
                    Column(
                        modifier = Modifier

                            .padding(16.dp)
                    ) {
                        Text(text = "Overview:", style = MaterialTheme.typography.headlineLarge)
                        Text(text = movie.overview, style = MaterialTheme.typography.bodyLarge)
                        RowText("Release date:", movie.releaseDate)
                        RowText("Original Language:", movie.languages)
                        RowText("Popularity:", movie.popularity.toString())
                        RowText("Vote average:", movie.voteAverage.toString())
                    }
                }
            }
        }
    }


}

@Composable
private fun RowText(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(text = value, style = MaterialTheme.typography.bodyLarge)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailTopAppBar(title: String, onBack: () -> Unit) {
    TopAppBar(title = {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "back",
                modifier = Modifier
                    .size(32.dp)
                    .clickable {
                        onBack()
                    })
            Text(
                text = title,
                modifier = Modifier.padding(start = 16.dp),
                style = MaterialTheme.typography.displaySmall
            )
        }
    })
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