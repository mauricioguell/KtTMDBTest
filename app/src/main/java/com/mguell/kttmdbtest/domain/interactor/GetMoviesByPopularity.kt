package com.mguell.kttmdbtest.domain.interactor

import com.mguell.kttmdbtest.domain.model.Movie
import com.mguell.kttmdbtest.domain.repository.MovieRepository
import io.reactivex.Observable

class GetMoviesByPopularity (private val movieRepository : MovieRepository): UseCase<List<Movie>, Int>() {

    override fun buildUseCaseObservable(params: Int): Observable<List<Movie>> {
        return this.movieRepository.moviesByPopularity(params)
    }
}
