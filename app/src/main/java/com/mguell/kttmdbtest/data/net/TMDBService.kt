package com.mguell.kttmdbtest.data.net

import com.mguell.kttmdbtest.data.net.response.MovieResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface TMDBService {

    /**
     * Returns the most popular movies.
     *
     * @param apiKey String with the key of the TMDB API.
     * @param page   int with the page number that the user want to see.
     * @return MovieResponse call with the list of the most popular at the desired page.
     */
    @Headers("Content-Type: application/json;charset=utf-8")
    @GET("movie/popular")
    fun moviesByPopularity(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Observable<MovieResponse>

    /**
     * Returns the movies that more closely match to the query.
     *
     * @param apiKey String with the key of the TMDB API.
     * @param query  String with the text that is necessary in the title at this search.
     * @param page   int with the page number that the user want to see.
     * @return MovieResponse call with the list of the movies that more closely match to the
     * query at the desired page.
     */
    @Headers("Content-Type: application/json;charset=utf-8")
    @GET("search/movie")
    fun moviesByQuery(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("page") page: Int
    ): Observable<MovieResponse>
}