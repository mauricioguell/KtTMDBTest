package com.mguell.kttmdbtest.domain.interactor

import com.mguell.kttmdbtest.domain.model.Movie
import com.mguell.kttmdbtest.domain.repository.MovieRepository
import io.reactivex.Observable

class GetMoviesByQuery(private val movieRepository: MovieRepository) : UseCase<List<Movie>, GetMoviesByQuery.Params>() {

    override fun buildUseCaseObservable(params: Params): Observable<List<Movie>> {
        return movieRepository.moviesByQuery(params.query, params.page)
    }

    class Params(
        val query: String,
        val page: Int
    )
}