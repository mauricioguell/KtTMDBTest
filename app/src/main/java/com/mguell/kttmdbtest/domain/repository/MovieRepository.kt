package com.mguell.kttmdbtest.domain.repository

import com.mguell.kttmdbtest.domain.model.Movie
import io.reactivex.Observable

interface MovieRepository {

    fun moviesByPopularity(page: Int): Observable<List<Movie>>

    fun moviesByQuery(
        query: String,
        page: Int
    ): Observable<List<Movie>>
}
