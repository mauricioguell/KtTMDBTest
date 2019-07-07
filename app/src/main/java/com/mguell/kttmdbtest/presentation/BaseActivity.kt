package com.mguell.kttmdbtest.presentation

import dagger.android.AndroidInjector
import dagger.android.AndroidInjection
import android.os.Bundle
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject
import dagger.android.HasAndroidInjector
import androidx.appcompat.app.AppCompatActivity


abstract class BaseActivity : AppCompatActivity(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun androidInjector(): AndroidInjector<Any>? {
        return androidInjector
    }
}
