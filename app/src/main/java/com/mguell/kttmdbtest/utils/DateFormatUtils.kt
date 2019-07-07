package com.mguell.kttmdbtest.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateFormatUtils {

    /**
     * Formats the movie release date from the TMDB API and returns its year as an int value.
     * If the date is not available, returns the current year.
     *
     * @return int with the release year of the movie.
     */
    @Throws(ParseException::class)
    fun getYear(releaseDate: String): Int {
        val sdf = SimpleDateFormat(
            Constants.TMDB_DATE_FORMAT,
            Locale.getDefault()
        )
        val calendar = GregorianCalendar()
        val date = sdf.parse(releaseDate)
        if (date == null) {
            throw ParseDateException(releaseDate, 0)
        } else {
            calendar.time = date
        }
        return calendar.get(Calendar.YEAR)
    }
}