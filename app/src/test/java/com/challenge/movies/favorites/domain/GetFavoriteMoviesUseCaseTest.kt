package com.challenge.movies.favorites.domain

import com.challenge.moviesmanager.data.MoviesResponse
import com.challenge.moviesmanager.data.db.entities.FavoriteMovieEntity
import com.challenge.moviesmanager.domain.MoviesRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetFavoriteMoviesUseCaseTest {
    private lateinit var movieRepository: MoviesRepository
    private lateinit var getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase
    private lateinit var favoriteMovieEntity: FavoriteMovieEntity


    @Before
    fun setUp() {
        movieRepository = mockk()
        getFavoriteMoviesUseCase = GetFavoriteMoviesUseCase(movieRepository)
        favoriteMovieEntity = FavoriteMovieEntity(
            id = 1L,
            voteAverage = 1.0,
            popularity = 1.0,
            languages = "en",
            overview = "overview",
            title = "title",
            posterPath = "posterPath",
            releaseDate = "releaseDate"
        )
    }

    @Test
    fun `when successfully fetching popular movies, then the uiState should be SUCCESS`() =
        runBlocking {
            coEvery { movieRepository.getFavoriteMovies() } returns flowOf(listOf( favoriteMovieEntity))
            getFavoriteMoviesUseCase()
            coVerify { movieRepository.getFavoriteMovies() }


        }

    @Test
    fun `when  fetching popular movies, then the uiState should be SUCCESS`() =
        runBlocking {
            coEvery { movieRepository.getFavoriteMovies() } returns flowOf(listOf(favoriteMovieEntity))
            val response = getFavoriteMoviesUseCase()
            response.collect {
                assert(it[0].id == 1L)
                assert(it[0].popularity == 1.0)
                assert(it[0].voteAverage == 1.0)
                assert(it[0].languages == "en")
                assert(it[0].overview == "overview")
                assert(it[0].title == "title")
                assert(it[0].poster == "posterPath")
                assert(it[0].releaseDate == "releaseDate")
            }
        }
}