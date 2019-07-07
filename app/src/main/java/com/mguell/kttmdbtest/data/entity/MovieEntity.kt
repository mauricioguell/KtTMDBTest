package com.mguell.kttmdbtest.data.entity

import com.google.gson.annotations.SerializedName

data class MovieEntity(
    @SerializedName("title")
    val title: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("poster_path")
    val posterPath: String
)
