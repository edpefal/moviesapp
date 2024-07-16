package com.challenge.movies.favorites.presentation

import com.challenge.favorites.domain.GetFavoriteMoviesUseCase
import com.challenge.shared.presentation.models.MoviesUiState
import com.challenge.moviesmanager.presentation.MovieModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test


class FavoriteMoviesViewModelTest {

    private lateinit var favoriteMoviesViewModel: com.challenge.favorites.presentation.FavoriteMoviesViewModel
    private lateinit var getFavoriteMoviesUseCase: com.challenge.favorites.domain.GetFavoriteMoviesUseCase

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var movieModel: MovieModel
    private lateinit var movieModelList: List<MovieModel>

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        getFavoriteMoviesUseCase = mockk()
        favoriteMoviesViewModel =
            com.challenge.favorites.presentation.FavoriteMoviesViewModel(getFavoriteMoviesUseCase)
        movieModel = MovieModel(
            id = 1,
            overview = "overview",
            popularity = 1.0,
            releaseDate = "releaseDate",
            title = "title",
            voteAverage = 1.0,
            languages = "en",
            poster = "poster"
        )
        movieModelList = listOf(movieModel)
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when movie list is not empty getMovies emits Success state`() = runTest {
        coEvery { getFavoriteMoviesUseCase() } returns flowOf(movieModelList)
        favoriteMoviesViewModel.getMovies()
        advanceUntilIdle()
        coVerify { getFavoriteMoviesUseCase() }
        assert(favoriteMoviesViewModel.moviesUiState.value == com.challenge.shared.presentation.models.MoviesUiState.Success(movieModelList))
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when movie list is empty then getMovies emits Empty state`() = runTest {

        coEvery { getFavoriteMoviesUseCase() } returns flowOf(emptyList())
        favoriteMoviesViewModel.getMovies()
        advanceUntilIdle()
        coVerify { getFavoriteMoviesUseCase() }
        assert(favoriteMoviesViewModel.moviesUiState.value == com.challenge.shared.presentation.models.MoviesUiState.Empty)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when there is an error fetching movies then getMovies emits Error state `() = runTest {
        coEvery { getFavoriteMoviesUseCase() } returns flow {
            throw Exception("Error")
        }
        favoriteMoviesViewModel.getMovies()
        advanceUntilIdle()
        coVerify { getFavoriteMoviesUseCase() }
        assert(favoriteMoviesViewModel.moviesUiState.value == com.challenge.shared.presentation.models.MoviesUiState.Error)
    }

}