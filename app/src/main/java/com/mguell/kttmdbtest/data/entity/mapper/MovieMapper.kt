package com.mguell.kttmdbtest.data.entity.mapper

import com.mguell.kttmdbtest.data.entity.MovieEntity
import com.mguell.kttmdbtest.data.net.response.MovieResponse
import com.mguell.kttmdbtest.domain.model.Movie
import com.mguell.kttmdbtest.utils.Constants
import com.mguell.kttmdbtest.utils.StringUtils
import java.util.*


class MovieMapper {

    fun transformList(movieResponse: MovieResponse): List<Movie> {
        val townships = ArrayList<Movie>()
        for (movieEntity in movieResponse.movies) {
            townships.add(transform(movieEntity))
        }
        return townships
    }

    private fun transform(movieEntity: MovieEntity): Movie {
        return Movie(
            parseTitle(movieEntity.title),
            parseOverview(movieEntity.overview),
            movieEntity.releaseDate,
            parsePosterPath(movieEntity.posterPath)
        )
    }

    private fun parseTitle(title: String): String {
        return StringUtils.processHtmlString(title)
    }

    private fun parseOverview(overview: String): String {
        return StringUtils.processHtmlString(overview)
    }

    /**
     * Generates the movie poster URL by adding the root URL to the movie poster path.
     * If there is not any movie poster path available, returns an empty String.
     *
     * @return String with the movie poster URL.
     */
    private fun parsePosterPath(posterPath: String): String {
        return String.format("%s%s", Constants.TMDB_POSTER_URL, posterPath)
    }
}
