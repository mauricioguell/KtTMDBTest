package com.mguell.kttmdbtest.deps

import dagger.Component
import javax.inject.Singleton
import com.mguell.kttmdbtest.App
import android.app.Application
import dagger.BindsInstance
import dagger.android.AndroidInjectionModule


@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, ActivityModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}
