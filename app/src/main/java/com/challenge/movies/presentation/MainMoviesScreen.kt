package com.challenge.movies.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.challenge.moviedetail.presentation.MovieDetailViewModel
import com.challenge.popular.presentation.PopularMoviesViewModel
import com.challenge.shared.presentation.models.BottomNavItem
import com.challenge.shared.data.routes.Routes


@Composable
fun MainMoviesScreen(
    popularMoviesViewModel: PopularMoviesViewModel,
    movieDetailViewModel: MovieDetailViewModel,
    favoriteMoviesViewModel: com.challenge.favorites.presentation.FavoriteMoviesViewModel,
    nowPlayingMoviesViewModel: com.challenge.nowplaying.presentation.NowPlayingMoviesViewModel
) {
    val navController = rememberNavController()
    val navItems = listOf(
        BottomNavItem(Routes.PopularMovies.route, Icons.Default.Star),
        BottomNavItem(Routes.NowPlaying.route, Icons.Default.PlayArrow),
        BottomNavItem(Routes.Favorites.route, Icons.Default.Favorite),
    )

    Scaffold(bottomBar = { BottomNavigationBar(navController, navItems) }) {
        NavHost(
            modifier = Modifier.padding(it),
            navController = navController,
            startDestination = Routes.PopularMovies.route
        ) {
            composable(Routes.PopularMovies.route) {
                MoviesListScreen(
                    "Popular Movies",
                    moviesViewModel = popularMoviesViewModel,
                    navController
                )
            }
            composable(
                Routes.MovieDetail.route,
                arguments = listOf(navArgument("movieId") {
                    type = NavType.LongType
                })
            ) { backStackEntry ->
                val movieId = backStackEntry.arguments?.getLong("movieId") ?: 0
                MovieDetailScreen(movieId, movieDetailViewModel, navController)
            }
            composable(Routes.Favorites.route) {
                MoviesListScreen(
                    "Favorite Movies",
                    moviesViewModel = favoriteMoviesViewModel,
                    navController
                )
            }
            composable(Routes.NowPlaying.route) {
                MoviesListScreen(
                    "Now Playing Movies",
                    moviesViewModel = nowPlayingMoviesViewModel,
                    navController
                )
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController, navItems: List<BottomNavItem>) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        navItems.forEach { item ->
            NavigationBarItem(
                selected = currentDestination?.route == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { Icon(item.icon, contentDescription = item.route) }
            )
        }
    }
}

