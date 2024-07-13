package com.challenge.movies.shared.presentation.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.challenge.movies.popular.presentation.PopularMoviesScreen
import com.challenge.movies.popular.presentation.PopularMoviesViewModel
import com.challenge.movies.shared.presentation.models.BottomNavItem


@Composable
fun MainMoviesScreen(popularMoviesViewModel: PopularMoviesViewModel) {
    val navController = rememberNavController()
    val navItems = listOf(
        BottomNavItem("popular", "Popular", Icons.Default.Star),
        BottomNavItem("playing", "Now Playing", Icons.Default.PlayArrow),
        BottomNavItem("Favorites", "favorites", Icons.Default.Favorite),
    )

    Scaffold(bottomBar = { BottomNavigationBar(navController, navItems) }) {
        NavHost(
            modifier = Modifier.padding(it),
            navController = navController,
            startDestination = "popular"
        ) {
            composable("popular") { PopularMoviesScreen(popularMoviesViewModel = popularMoviesViewModel) }
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
                label = {Text(item.title)},
                selected = currentDestination?.route == item.route,
                onClick = { navController.navigate(item.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                } },
                icon = { Icon(item.icon, contentDescription = item.title) }
            )
        }
    }
}

