package com.codingwithmitch.daggerpractice.di

import android.app.Application
import com.codingwithmitch.daggerpractice.BaseApplication
import com.codingwithmitch.daggerpractice.SessionManager
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class, // <- always need this
    ActivityBuildersModule::class,
    AppModule::class,
    ViewModelFactoryModule::class]
)
interface AppComponent : AndroidInjector<BaseApplication> {

    fun sessionManager() : SessionManager

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build() : AppComponent
    }
}