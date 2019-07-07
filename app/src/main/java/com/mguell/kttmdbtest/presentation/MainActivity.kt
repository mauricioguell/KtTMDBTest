package com.mguell.kttmdbtest.presentation

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import com.guell.mauricio.sample_tmdb.R
import com.mguell.kttmdbtest.presentation.view.landing.LandingFragment
import com.mguell.kttmdbtest.presentation.view.no_internet.NoInternetFragment
import com.mguell.kttmdbtest.presentation.view.no_results.NoResultsFragment
import com.mguell.kttmdbtest.utils.Constants
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity(), NoInternetFragment.RetryClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val mSearchView = searchItem.actionView as SearchView
        mSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(s: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                val currentFragment = supportFragmentManager.findFragmentById(R.id.container)
                if (currentFragment is LandingFragment) {
                    currentFragment.onQueryTextChange(newText)
                } else if (currentFragment is NoResultsFragment) {
                    openLandingFragment(newText)
                }
                return false
            }
        })
        openLandingFragment("")
        return true
    }

    /**
     * Opens a new LandingFragment in order to try to connect to the TMDB API, passing
     * the current text query at the SearchView.
     */
    override fun retryConnection() {
        openLandingFragment("")
    }

    private fun openLandingFragment(queryText: String) {
        val landingFragment = LandingFragment.newInstance()
        val args = Bundle()
        if (queryText.isEmpty()) {
            args.putString(Constants.QUERY_TEXT_KEY, "")
        } else {
            args.putString(Constants.QUERY_TEXT_KEY, queryText)
        }
        landingFragment.arguments = args
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, landingFragment)
            .commitAllowingStateLoss()
    }

    fun openNoResultsFragment(queryText: String) {
        val noResultsFragment = NoResultsFragment.newInstance()
        val args = Bundle()
        args.putString(Constants.QUERY_TEXT_KEY, queryText)
        noResultsFragment.arguments = args
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, noResultsFragment)
            .commitAllowingStateLoss()
    }

    fun openConnectionError() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, NoInternetFragment.newInstance())
            .commitAllowingStateLoss()
    }
}
