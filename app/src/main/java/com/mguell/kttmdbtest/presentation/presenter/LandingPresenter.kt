package com.mguell.kttmdbtest.presentation.presenter

import android.util.Log
import android.view.View
import com.google.gson.Gson
import com.mguell.kttmdbtest.domain.interactor.GetMoviesByPopularity
import com.mguell.kttmdbtest.domain.interactor.GetMoviesByQuery
import com.mguell.kttmdbtest.domain.model.Movie
import com.mguell.kttmdbtest.presentation.view.adapter.PagingScrollListener
import com.mguell.kttmdbtest.presentation.view.landing.LandingView
import com.mguell.kttmdbtest.utils.Constants
import io.reactivex.observers.DisposableObserver


class LandingPresenter(
    private val getMoviesByPopularity: GetMoviesByPopularity,
    private val getMoviesByQuery: GetMoviesByQuery
) : PagingScrollListener.LoadMoreItemsListener {

    private lateinit var view: LandingView

    init {
        initializePage()
    }

    companion object {
        private val TAG = this::class.java.simpleName
    }

    fun setView(view: LandingView) {
        this.view = view
    }

    private var currentPage: Int = 0

    private var queryText: String = ""

    private fun initializePage() {
        this.currentPage = 1
    }

    private fun getQueryText(): String {
        return queryText
    }

    /**
     * Sets the value of the queryText, updates the displayed list and scrolls it
     * to its top.
     *
     * @param newText String with the new value of the queryText.
     */
    fun setQueryText(newText: String) {
        initializePage()
        this.queryText = newText
        if (this.queryText.isEmpty()) {
            showMoviesByPopularity()
        } else {
            showMoviesByQuery(this.queryText)
        }
        view.scrollToTop()
    }

    /**
     * Fills the RecyclerView adapter with the most popular movies from TMDB.
     */
    private fun showMoviesByPopularity() {
        view.setLoadingMoviesBarVisibility(View.VISIBLE)
        this.getMoviesByPopularity.execute(MovieListByPopularityObserver(), currentPage)
    }

    /**
     * Fills the RecyclerView adapter with the movies with a most similar title
     * to the String param from TMDB.
     *
     * @param text String with the text that is going to be compared to the movie titles.
     */
    private fun showMoviesByQuery(text: String) {
        view.setLoadingMoviesBarVisibility(View.VISIBLE)
        getMoviesByQuery.execute(MovieListByQueryObserver(), GetMoviesByQuery.Params(text, currentPage))
    }

    override fun loadMoreItems(page: Int) {
        this.currentPage = page
        if (getQueryText().isEmpty()) {
            showMoviesByPopularity()
        } else {
            showMoviesByQuery(getQueryText())
        }
    }

    private inner class MovieListByPopularityObserver : DisposableObserver<List<Movie>>() {

        override fun onComplete() {
            Log.i(TAG, "showMoviesByPopularity completed")
        }

        override fun onError(e: Throwable) {
            Log.e(TAG, "getMoviesByPopularity onError: $e")
            view.getParent()?.openConnectionError()
        }

        override fun onNext(movieList: List<Movie>) {
            view.setLoadingMoviesBarVisibility(View.GONE)
            if (currentPage == 1) {
                view.replaceMovies(movieList)
            } else {
                view.addMovies(movieList)
            }
            Log.d(TAG, "getMoviesByPopularity response = " + Gson().toJson(movieList))
        }
    }

    private inner class MovieListByQueryObserver : DisposableObserver<List<Movie>>() {

        override fun onComplete() {
            Log.i(TAG, "showMoviesByQuery completed")
        }

        override fun onError(e: Throwable) {
            Log.e(TAG, "getMoviesByQuery onError: $e")
            view.getParent()?.openConnectionError()
        }

        override fun onNext(movies: List<Movie>) {
            if (currentPage == 1) {
                if (movies.isEmpty()) {
                    view.getParent()?.openNoResultsFragment(queryText)
                } else {
                    Log.d(TAG, "getFilteredMovies replacing")
                    view.replaceMovies(movies)
                }
            } else {
                Log.d(TAG, "getFilteredMovies adding")
                view.addMovies(movies)
            }
            if (movies.size < Constants.QUERY_NUM_RESULTS)
                view.markLastPage()
            view.setLoadingMoviesBarVisibility(View.GONE)
        }
    }
}
