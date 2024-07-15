package com.challenge.movies.popular.data

import com.challenge.movies.popular.data.db.dao.FavoriteMovieDao
import com.challenge.movies.popular.data.db.entities.FavoriteMovieEntity
import com.challenge.movies.popular.domain.MoviesRepository
import com.challenge.movies.popular.presentation.PopularMovieModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val popularMoviesService: PopularMoviesService,
    private val favoriteMoviesDao: FavoriteMovieDao
) : MoviesRepository {

    override suspend fun getPopularMovies(page: Int): Flow<PopularMoviesResponse> {
        return popularMoviesService.getPopularMovies(page)
    }

    override suspend fun getMovieById(id: Long): Flow<PopularMovie> {
        return popularMoviesService.getMovieById(id)
    }

    override suspend fun saveFavoriteMovie(movie: PopularMovieModel) {
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

    override suspend fun getNowPlayingMovies(page: Int): Flow<PopularMoviesResponse> {
        return popularMoviesService.getNowPlayingMovies(page)
    }

    private fun PopularMovieModel.toFavoriteMovieEntity(): FavoriteMovieEntity {
        return FavoriteMovieEntity(
            id = id,
            title = title,
            overview = overview,
            posterPath = poster,
            releaseDate = releaseDate,
        )
    }

}