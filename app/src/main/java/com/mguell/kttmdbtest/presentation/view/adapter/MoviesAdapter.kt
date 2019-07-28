package com.mguell.kttmdbtest.presentation.view.adapter

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.guell.mauricio.sample_tmdb.R
import com.mguell.kttmdbtest.domain.model.Movie
import com.mguell.kttmdbtest.utils.DateFormatUtils
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.movie_card.view.*
import java.util.*
import java.text.ParseException


/**
 * Adapter for managing the movies information that will be displayed to the user.
 */

class MoviesAdapter :
    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    companion object {
        private val TAG = this::class.java.simpleName
    }

    private val mMovies: MutableList<Movie> = ArrayList()

    fun addMovies(movies: List<Movie>) {
        mMovies.addAll(movies)
        notifyDataSetChanged()
    }

    fun replaceMovies(movies: List<Movie>) {
        mMovies.clear()
        addMovies(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_card, parent, false)
        return MovieViewHolder(v)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = mMovies[position]
        try {
            holder.titleAndYear.text = String.format(
                "%s, %s",
                movie.title,
                DateFormatUtils.getYear(movie.releaseDate)
            )
        } catch (e: ParseException) {
            holder.titleAndYear.text = String.format(
                "%s, %s",
                movie.title,
                holder.txtUnknownYear
            )
        }

        holder.overview.text = if (movie.overview.isEmpty()) holder.noOverviewAvailableText else movie.overview

        val transformation = RoundedCornersTransformation(
            holder.posterCornerRadius, holder.posterCornerMargin
        )
        if (movie.posterPath.isEmpty()) {
            Log.d(TAG, String.format("%s: %s", "Image not found for", movie.title))
            holder.poster.setImageDrawable(holder.noImageIc)
        } else {
            Picasso.get()
                .load(mMovies[position].posterPath)
                .transform(transformation)
                .into(holder.poster)
        }
    }

    override fun getItemCount(): Int {
        return mMovies.size
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val noOverviewAvailableText: String = itemView.resources.getString(R.string.no_overview_available)
        val posterCornerRadius: Int = itemView.resources.getDimension(R.dimen.poster_corner_radius).toInt()
        val posterCornerMargin: Int = itemView.resources.getDimension(R.dimen.poster_corner_margin).toInt()
        val txtUnknownYear: String = itemView.resources.getString(R.string.txt_unknown_year)
        val noImageIc: Drawable? = ContextCompat.getDrawable(itemView.context, R.drawable.ic_no_image)
        val titleAndYear: AppCompatTextView = itemView.card_movie_title_and_year
        val overview: AppCompatTextView = itemView.card_movie_overview
        val poster: AppCompatImageView = itemView.card_movie_poster
    }
}
