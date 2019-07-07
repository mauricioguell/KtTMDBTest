package com.mguell.kttmdbtest

import com.mguell.kttmdbtest.data.entity.MovieEntity
import com.mguell.kttmdbtest.data.net.response.MovieResponse
import com.mguell.kttmdbtest.data.repository.MovieDataRepository
import com.mguell.kttmdbtest.data.repository.datasource.MovieDataStore
import com.mguell.kttmdbtest.domain.repository.MovieRepository
import io.reactivex.Observable

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class MovieDataRepositoryTest {

    @Mock
    private lateinit var networkMovieDataStore: MovieDataStore

    private lateinit var movieDataRepository: MovieRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        movieDataRepository = MovieDataRepository(networkMovieDataStore)
    }

    @Test
    fun should_return_a_list_with_popular_movies() {
        val popularMoviesResponse = givenMovies()
        `when`(networkMovieDataStore.moviesByPopularity(anyInt())).thenReturn(
            Observable.just(
                popularMoviesResponse
            )
        )

        movieDataRepository.moviesByPopularity(0)
            .test()
            .assertComplete()
            .assertValue { movies -> movies.isNotEmpty() }
    }

    @Test
    fun should_return_a_list_with_movies_by_query() {
        val moviesByQueryResponse = givenMovies()
        `when`(networkMovieDataStore.moviesByQuery(anyString(), anyInt())).thenReturn(
            Observable.just(
                moviesByQueryResponse
            )
        )
        movieDataRepository.moviesByQuery("spiderman", 0)
            .test()
            .assertComplete()
            .assertValue { movies -> movies.isNotEmpty() }
    }

    private fun givenMovies() : MovieResponse {
        val movieResponse = MovieResponse()
        val movieEntityList : ArrayList<MovieEntity>  = ArrayList()

        val spiderman = MovieEntity(
            "Spider-Man: Far from Home",
            "Peter Parker and his friends go on a summer trip to Europe. However, they will hardly be able to rest - Peter will have to agree to help Nick Fury uncover the mystery of creatures that cause natural disasters and destruction throughout the continent.",
            "2019-06-28",
            "/2cAc4qH9Uh2NtSujJ90fIAMrw7T.jpg"
        )

        val toyStory = MovieEntity(
            "Toy Story 4",
            "Woody has always been confident about his place in the world and that his priority is taking care of his kid, whether that's Andy or Bonnie. But when Bonnie adds a reluctant new toy called \"Forky\" to her room, a road trip adventure alongside old and new friends will show Woody how big the world can be for a toy.",
            "2019-06-19",
            "/w9kR8qbmQ01HwnvK4alvnQ2ca0L.jpg"
        )
        movieEntityList.add(spiderman)
        movieEntityList.add(toyStory)
        movieResponse.setMovies(movieEntityList)
        return movieResponse
    }
}
