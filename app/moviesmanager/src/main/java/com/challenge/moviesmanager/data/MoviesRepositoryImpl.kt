package com.challenge.moviesmanager.data

import com.challenge.moviesmanager.data.db.dao.FavoriteMovieDao
import com.challenge.moviesmanager.data.db.entities.FavoriteMovieEntity
import com.challenge.moviesmanager.domain.MoviesRepository
import com.challenge.moviesmanager.presentation.MovieModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val moviesService: MoviesService,
    private val favoriteMoviesDao: FavoriteMovieDao
) : MoviesRepository {

    override suspend fun getPopularMovies(page: Int): Flow<MoviesResponse> {
        return moviesService.getPopularMovies(page)
    }

    override suspend fun getMovieById(id: Long): Flow<Movie> {
        return moviesService.getMovieById(id)
    }

    override suspend fun saveFavoriteMovie(movie: MovieModel) {
        favoriteMoviesDao.upsert(movie.toFavoriteMovieEntity())
    }

    override suspend fun deleteFavoriteMovie(movieId: Long) {
        favoriteMoviesDao.deleteFavoriteMovieById(movieId)
    }

    override suspend fun getFavoriteMovies(): Flow<List<FavoriteMovieEntity>> {
        return flow {
            val result = favoriteMoviesDao.getAllFavoriteMovies()
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getNowPlayingMovies(page: Int): Flow<MoviesResponse> {
        return moviesService.getNowPlayingMovies(page)
    }

    private fun MovieModel.toFavoriteMovieEntity(): FavoriteMovieEntity {
        return FavoriteMovieEntity(
            id = id,
            title = title,
            overview = overview,
            posterPath = poster,
            releaseDate = releaseDate,
            voteAverage = voteAverage,
            popularity = popularity,
            languages = languages
        )
    }

}