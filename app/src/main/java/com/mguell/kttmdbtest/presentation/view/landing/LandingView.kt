package com.mguell.kttmdbtest.presentation.view.landing

import androidx.recyclerview.widget.RecyclerView
import com.mguell.kttmdbtest.domain.model.Movie
import com.mguell.kttmdbtest.presentation.MainActivity

interface LandingView {

    fun isLoadingMoviesBarVisible(): Boolean

    fun getLayoutManager(): RecyclerView.LayoutManager

    fun getMoviesCount(): Int

    fun getParent(): MainActivity?

    fun setLoadingMoviesBarVisibility(visibility: Int)

    fun replaceMovies(movies: List<Movie>)

    fun addMovies(movies: List<Movie>)

    fun scrollToTop()
}