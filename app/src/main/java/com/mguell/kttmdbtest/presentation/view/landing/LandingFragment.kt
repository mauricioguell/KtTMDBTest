package com.mguell.kttmdbtest.presentation.view.landing

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.guell.mauricio.sample_tmdb.R
import com.mguell.kttmdbtest.domain.model.Movie
import com.mguell.kttmdbtest.presentation.MainActivity
import com.mguell.kttmdbtest.presentation.presenter.LandingPresenter
import com.mguell.kttmdbtest.presentation.view.BaseFragment
import com.mguell.kttmdbtest.presentation.view.adapter.MoviesAdapter
import com.mguell.kttmdbtest.utils.Constants
import com.mguell.kttmdbtest.utils.RecyclerViewMargin
import kotlinx.android.synthetic.main.landing_fragment.pbLoadingMovies
import kotlinx.android.synthetic.main.landing_fragment.*
import javax.inject.Inject


class LandingFragment : BaseFragment(), LandingView {

    @Inject
    lateinit var landingPresenter: LandingPresenter

    @Inject
    lateinit var ctx: Context

    /**
     * ScrollListener that checks if the last item is displayed, if it is, loads another
     * page of movies, by popularity or by query depending on the current type of search.
     */
    private val mScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            landingPresenter.scrollMovieList()
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.landing_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rvCards.layoutManager = LinearLayoutManager(ctx)
        rvCards.setHasFixedSize(true)
        rvCards.adapter = MoviesAdapter()
        rvCards.addOnScrollListener(mScrollListener)
        val decoration = RecyclerViewMargin(
            resources.getDimension(R.dimen.distance_between_recycler_view_items).toInt(),
            resources.getInteger(R.integer.movie_list_column_number)
        )
        rvCards.addItemDecoration(decoration)
        landingPresenter.setView(this)
        if (arguments != null) {
            this.landingPresenter.setQueryText(arguments!!.getString(Constants.QUERY_TEXT_KEY, ""))
        } else {
            this.landingPresenter.setQueryText("")
        }
    }

    override fun setLoadingMoviesBarVisibility(visibility: Int) {
        pbLoadingMovies?.visibility = visibility
    }

    override fun isLoadingMoviesBarVisible(): Boolean {
        return pbLoadingMovies.isShown
    }

    override fun replaceMovies(movies: List<Movie>) {
        (rvCards?.adapter as MoviesAdapter?)?.replaceMovies(movies)
    }

    override fun addMovies(movies: List<Movie>) {
        (rvCards?.adapter as MoviesAdapter).addMovies(movies)
    }

    override fun scrollToTop() {
        rvCards?.scrollToPosition(0)
    }

    override fun getLayoutManager(): RecyclerView.LayoutManager {
        return rvCards?.layoutManager!!
    }

    override fun getMoviesCount(): Int {
        return rvCards?.adapter?.itemCount!!
    }

    override fun getParent(): MainActivity? {
        return activity as MainActivity?
    }

    fun onQueryTextChange(queryText: String) {
        this.landingPresenter.setQueryText(queryText)
    }

    companion object {

        fun newInstance(): LandingFragment {
            return LandingFragment()
        }
    }
}
