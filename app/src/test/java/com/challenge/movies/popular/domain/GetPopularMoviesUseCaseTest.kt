package com.challenge.movies.popular.domain

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

class GetPopularMoviesUseCaseTest {

    private lateinit var movieRepository: MoviesRepository
    private lateinit var getPopularMoviesUseCase: GetPopularMoviesUseCase
    private lateinit var movieResponse: MoviesResponse
    private lateinit var movie: Movie
    private val page = 1
    private val totalPages = 1


    @Before
    fun setUp() {
        movieRepository = mockk()
        getPopularMoviesUseCase = GetPopularMoviesUseCase(movieRepository)
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
            coEvery { movieRepository.getPopularMovies(page) } returns flowOf(MoviesResponse())
            getPopularMoviesUseCase(page)
            coVerify { movieRepository.getPopularMovies(page) }


        }

    @Test
    fun `when  fetching popular movies, then the uiState should be SUCCESS`() =
        runBlocking {
            coEvery { movieRepository.getPopularMovies(1) } returns flowOf(movieResponse)
            val response = getPopularMoviesUseCase(1)
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