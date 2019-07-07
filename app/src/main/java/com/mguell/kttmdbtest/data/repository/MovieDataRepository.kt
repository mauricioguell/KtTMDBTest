package com.mguell.kttmdbtest.data.repository


import com.mguell.kttmdbtest.data.entity.mapper.MovieMapper
import com.mguell.kttmdbtest.data.repository.datasource.IMovieDataStore
import com.mguell.kttmdbtest.domain.model.Movie
import com.mguell.kttmdbtest.domain.repository.MovieRepository
import io.reactivex.Observable

class MovieDataRepository(private val movieDataStore: IMovieDataStore) : MovieRepository {

    private val movieMapper: MovieMapper = MovieMapper()

    override fun moviesByPopularity(page: Int): Observable<List<Movie>> {
        return movieDataStore.moviesByPopularity(page).map(movieMapper::transformList)
    }

    override fun moviesByQuery(query: String, page: Int): Observable<List<Movie>> {
        return movieDataStore.moviesByQuery(query, page).map(movieMapper::transformList)
    }
}
