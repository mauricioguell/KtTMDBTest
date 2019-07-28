package com.mguell.kttmdbtest.deps

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import com.mguell.kttmdbtest.data.repository.MovieDataRepository
import com.mguell.kttmdbtest.data.repository.datasource.MovieDataStore
import com.mguell.kttmdbtest.domain.repository.MovieRepository


@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideMovieRepository(): MovieRepository {
        return MovieDataRepository(MovieDataStore())
    }
}