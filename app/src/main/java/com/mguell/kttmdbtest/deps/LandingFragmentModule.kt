package com.mguell.kttmdbtest.deps

import com.mguell.kttmdbtest.domain.interactor.GetMoviesByQuery
import dagger.Provides
import com.mguell.kttmdbtest.domain.interactor.GetMoviesByPopularity
import com.mguell.kttmdbtest.presentation.presenter.LandingPresenter
import dagger.Module
import com.mguell.kttmdbtest.domain.repository.MovieRepository


@Module
class LandingFragmentModule {

    @Provides
    fun provideLandingPresenter(
        getMoviesByPopularity: GetMoviesByPopularity,
        getMoviesByQuery: GetMoviesByQuery
    ): LandingPresenter {
        return LandingPresenter(getMoviesByPopularity, getMoviesByQuery)
    }

    @Provides
    fun provideGetMoviesByPopularity(movieRepository: MovieRepository): GetMoviesByPopularity {
        return GetMoviesByPopularity(movieRepository)
    }

    @Provides
    fun provideGetMoviesByQuery(movieRepository: MovieRepository): GetMoviesByQuery {
        return GetMoviesByQuery(movieRepository)
    }
}