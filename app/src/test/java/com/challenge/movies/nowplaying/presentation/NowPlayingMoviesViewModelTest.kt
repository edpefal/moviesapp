package com.challenge.movies.nowplaying.presentation

import com.challenge.nowplaying.domain.GetNowPlayingMoviesUseCase
import com.challenge.shared.presentation.models.MoviesUiState
import com.challenge.moviesmanager.presentation.MovieListModel
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


class NowPlayingMoviesViewModelTest {


    private lateinit var nowPlayingMoviesViewModel: com.challenge.nowplaying.presentation.NowPlayingMoviesViewModel
    private lateinit var getNowPlayingMoviesUseCase: com.challenge.nowplaying.domain.GetNowPlayingMoviesUseCase

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var movieListModel: MovieListModel
    private lateinit var movieModel: MovieModel
    private val page = 1
    private val totalPages = 1
    private lateinit var movieModelList: List<MovieModel>

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        getNowPlayingMoviesUseCase = mockk()
        nowPlayingMoviesViewModel = com.challenge.nowplaying.presentation.NowPlayingMoviesViewModel(
            getNowPlayingMoviesUseCase
        )
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

        movieListModel = MovieListModel(totalPages = totalPages, results = movieModelList)
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when movie list is not empty getMovies emits Success state`() = runTest {
        coEvery { getNowPlayingMoviesUseCase.invoke(page) } returns flowOf(movieListModel)
        nowPlayingMoviesViewModel.getMovies()
        advanceUntilIdle()
        coVerify { getNowPlayingMoviesUseCase.invoke(page) }
        assert(nowPlayingMoviesViewModel.moviesUiState.value == com.challenge.shared.presentation.models.MoviesUiState.Success(movieModelList))
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when movie list is empty then getMovies emits Empty state`() = runTest {
        movieListModel = movieListModel.copy(results = emptyList())
        coEvery { getNowPlayingMoviesUseCase.invoke(page) } returns flowOf(movieListModel)
        nowPlayingMoviesViewModel.getMovies()
        advanceUntilIdle()
        coVerify { getNowPlayingMoviesUseCase.invoke(page) }
        assert(nowPlayingMoviesViewModel.moviesUiState.value == com.challenge.shared.presentation.models.MoviesUiState.Empty)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when there is an error fetching movies then getMovies emits Error state `() = runTest {
        coEvery { getNowPlayingMoviesUseCase.invoke(page) } returns flow {
            throw Exception("Error")
        }
        nowPlayingMoviesViewModel.getMovies()
        advanceUntilIdle()
        coVerify { getNowPlayingMoviesUseCase.invoke(page) }
        assert(nowPlayingMoviesViewModel.moviesUiState.value == com.challenge.shared.presentation.models.MoviesUiState.Error)
    }

}