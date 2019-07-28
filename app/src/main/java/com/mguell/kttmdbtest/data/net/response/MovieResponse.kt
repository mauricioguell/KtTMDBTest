package com.mguell.kttmdbtest.data.net.response

import com.google.gson.annotations.SerializedName
import com.mguell.kttmdbtest.data.entity.MovieEntity


/**
 * Response of the TMDB API calls, with a List of Movie objects.
 */

data class MovieResponse (@SerializedName("results") val movies: List<MovieEntity>)
