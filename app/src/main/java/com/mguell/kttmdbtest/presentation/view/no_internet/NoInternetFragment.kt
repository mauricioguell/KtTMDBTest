package com.mguell.kttmdbtest.presentation.view.no_internet

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.guell.mauricio.sample_tmdb.R
import kotlinx.android.synthetic.main.no_internet_fragment.*


class NoInternetFragment : Fragment() {

    private lateinit var mCallback: RetryClickListener

    override fun onCreateView(
        @NonNull inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.no_internet_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        tvRetry.setOnTouchListener { _: View, event: MotionEvent ->
            retry(event)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            mCallback = context as RetryClickListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement OnHeadlineSelectedListener")
        }
    }

    /**
     * Action of touching the retry TextView, if the event is the start of the touch event,
     * changes the color of the TextView (in order to give some feedback to the user), if the
     * event is the finish, tries to connect to the API via the MainActivity.
     *
     * @param motionEvent press or stop pressing the TextView event.
     * @return true if the method is correctly finished, false otherwise.
     */
    private fun retry(motionEvent: MotionEvent): Boolean {
        if (motionEvent.action == MotionEvent.ACTION_DOWN) {
            tvRetry.setTextColor(ContextCompat.getColor(requireContext(), R.color.accent_dark))
        } else if (motionEvent.action == MotionEvent.ACTION_UP) {
            mCallback.retryConnection()
        }
        return true
    }

    interface RetryClickListener {
        fun retryConnection()
    }

    companion object {

        fun newInstance(): NoInternetFragment {
            return NoInternetFragment()
        }
    }
}
