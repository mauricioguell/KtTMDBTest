package com.mguell.kttmdbtest.data.repository.datasource

import com.mguell.kttmdbtest.data.net.response.MovieResponse
import io.reactivex.Observable

interface IMovieDataStore {

    fun moviesByPopularity(page: Int): Observable<MovieResponse>

    fun moviesByQuery(
        query: String,
        page: Int
    ): Observable<MovieResponse>
}
