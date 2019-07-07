package com.mguell.kttmdbtest.deps

import com.mguell.kttmdbtest.presentation.view.landing.LandingFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector(modules = [LandingFragmentModule::class])
    abstract fun landingFragment(): LandingFragment
}