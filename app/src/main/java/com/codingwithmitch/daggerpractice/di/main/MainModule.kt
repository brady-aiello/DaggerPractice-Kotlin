package com.codingwithmitch.daggerpractice.di.main

import com.codingwithmitch.daggerpractice.network.main.MainApi
import com.codingwithmitch.daggerpractice.ui.main.posts.PostsRecyclerViewAdapter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object MainModule {
    @MainScope
    @JvmStatic
    @Provides
    fun provideMainApi(retrofit: Retrofit): MainApi {
        return retrofit.create(MainApi::class.java)
    }

    @MainScope
    @JvmStatic
    @Provides
    fun providePostsRecyclerAdapter(): PostsRecyclerViewAdapter {
        return PostsRecyclerViewAdapter()
    }
}