package com.mguell.kttmdbtest.data.repository.datasource

import com.mguell.kttmdbtest.data.net.RestClient
import com.mguell.kttmdbtest.data.net.response.MovieResponse
import com.mguell.kttmdbtest.utils.Constants
import io.reactivex.Observable

class MovieDataStore : IMovieDataStore {

    override fun moviesByPopularity(page: Int): Observable<MovieResponse> {
        return RestClient.tmdbService.moviesByPopularity(Constants.TMDB_API_KEY, page)
    }

    override fun moviesByQuery(query: String, page: Int): Observable<MovieResponse> {
        return RestClient.tmdbService.moviesByQuery(Constants.TMDB_API_KEY, query, page)
    }
}
