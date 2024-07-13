package com.challenge.movies.moviedetail

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@Composable
fun MovieDetailScreen(movieId: Long) {
    Scaffold(topBar = { MovieDetailTopAppBar()}) {

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailTopAppBar() {
    TopAppBar(title = { Text("Movie Detail") })
}