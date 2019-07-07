package com.mguell.kttmdbtest.data.net.response

import com.google.gson.annotations.SerializedName
import com.mguell.kttmdbtest.data.entity.MovieEntity


/**
 * Response of the TMDB API calls, with a List of Movie objects.
 */

class MovieResponse {

    @SerializedName("results")
    private lateinit var movies: List<MovieEntity>

    /**
     * Gets the Movie List.
     *
     * @return the Movie List, if it does not exist, returns an empty List.
     */
    fun getMovies(): List<MovieEntity> {
        return movies
    }

    fun setMovies(movies: List<MovieEntity>) {
        this.movies = movies
    }
}
