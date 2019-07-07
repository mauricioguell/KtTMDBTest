package com.mguell.kttmdbtest.presentation.view

import android.content.Context
import androidx.fragment.app.Fragment
import dagger.android.support.AndroidSupportInjection


abstract class BaseFragment : Fragment() {

    override fun onAttach(context: Context) {
        initDagger()
        super.onAttach(context)
    }

    private fun initDagger() {
        AndroidSupportInjection.inject(this)
    }
}
