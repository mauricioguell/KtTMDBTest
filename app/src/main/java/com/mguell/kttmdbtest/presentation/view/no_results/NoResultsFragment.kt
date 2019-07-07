package com.mguell.kttmdbtest.presentation.view.no_results

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.guell.mauricio.sample_tmdb.R
import com.mguell.kttmdbtest.utils.Constants
import kotlinx.android.synthetic.main.no_results_found_fragment.*

class NoResultsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.no_results_found_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val noResultsForCompleteText = String.format(
            "%s %s",
            getString(R.string.no_results_found_for),
            arguments!!.getString(Constants.QUERY_TEXT_KEY, "")
        )
        tvNoResultsFound.text = noResultsForCompleteText
    }

    companion object {

        fun newInstance(): NoResultsFragment {
            return NoResultsFragment()
        }
    }
}
