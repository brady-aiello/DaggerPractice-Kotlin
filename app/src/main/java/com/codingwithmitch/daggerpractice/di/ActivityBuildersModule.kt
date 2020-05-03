package com.codingwithmitch.daggerpractice.di

import com.codingwithmitch.daggerpractice.di.auth.AuthModule
import com.codingwithmitch.daggerpractice.di.auth.AuthScope
import com.codingwithmitch.daggerpractice.di.auth.AuthViewModelsModule
import com.codingwithmitch.daggerpractice.di.main.MainFragmentBuildersModule
import com.codingwithmitch.daggerpractice.di.main.MainModule
import com.codingwithmitch.daggerpractice.di.main.MainScope
import com.codingwithmitch.daggerpractice.di.main.MainViewModelsModule
import com.codingwithmitch.daggerpractice.ui.auth.AuthActivity
import com.codingwithmitch.daggerpractice.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {
    @AuthScope
    @ContributesAndroidInjector( // <- tells Dagger generate subcomponents
        modules = [AuthViewModelsModule::class, AuthModule::class])
    abstract fun contributeAuthActivity(): AuthActivity

    @MainScope
    @ContributesAndroidInjector(
        modules = [MainFragmentBuildersModule::class,
        MainViewModelsModule::class, MainModule::class]
    )
    abstract fun contributeMainActivity(): MainActivity
}