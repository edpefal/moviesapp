package com.challenge.movies.nowplaying.domain

import com.challenge.moviesmanager.data.Movie
import com.challenge.moviesmanager.data.MoviesResponse
import com.challenge.moviesmanager.domain.MoviesRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetNowPlayingMoviesUseCaseTest {

    private lateinit var movieRepository: MoviesRepository
    private lateinit var getNowPlayingMoviesUseCase: com.challenge.nowplaying.domain.GetNowPlayingMoviesUseCase
    private lateinit var movieResponse: MoviesResponse
    private lateinit var movie: Movie
    private val page = 1
    private val totalPages = 1


    @Before
    fun setUp() {
        movieRepository = mockk()
        getNowPlayingMoviesUseCase =
            com.challenge.nowplaying.domain.GetNowPlayingMoviesUseCase(movieRepository)
        movie = Movie(
            adult = false,
            backdropPath = "",
            genreIDS = emptyList(),
            id = 1,
            originalLanguage = "en",
            originalTitle = "originalTitle",
            overview = "overview",
            popularity = 1.0,
            posterPath = "posterPath",
            releaseDate = "releaseDate",
            title = "title",
            video = false,
            voteAverage = 1.0,
            voteCount = 1
        )
        movieResponse =
            MoviesResponse(
                page = page,
                results = listOf(movie),
                totalPages = totalPages,
                totalResults = 1
            )
    }

    @Test
    fun `when successfully fetching popular movies, then the uiState should be SUCCESS`() =
        runBlocking {
            coEvery { movieRepository.getNowPlayingMovies(page) } returns flowOf(MoviesResponse())
            getNowPlayingMoviesUseCase(page)
            coVerify { movieRepository.getNowPlayingMovies(page) }


        }

    @Test
    fun `when  fetching popular movies, then the uiState should be SUCCESS`() =
        runBlocking {
            coEvery { movieRepository.getNowPlayingMovies(page) } returns flowOf(movieResponse)
            val response = getNowPlayingMoviesUseCase(page)
            response.collect {
                assert(it.totalPages == totalPages)
                assert(it.results[0].title == movie.title)
                assert(it.results[0].poster == movie.posterPath)
                assert(it.results[0].voteAverage == movie.voteAverage)
                assert(it.results[0].releaseDate == movie.releaseDate)
                assert(it.results[0].popularity == movie.popularity)
                assert(it.results[0].languages == movie.originalLanguage)
                assert(it.results[0].overview == movie.overview)
            }
        }
}