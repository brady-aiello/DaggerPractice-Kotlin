package com.codingwithmitch.daggerpractice.di.main

import com.codingwithmitch.daggerpractice.ui.main.posts.PostsFragment
import com.codingwithmitch.daggerpractice.ui.main.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeProfileFragment(): ProfileFragment

    @ContributesAndroidInjector
    abstract fun contributePostsFragment(): PostsFragment
}