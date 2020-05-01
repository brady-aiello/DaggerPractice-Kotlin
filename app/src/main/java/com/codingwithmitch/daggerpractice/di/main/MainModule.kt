package com.codingwithmitch.daggerpractice.di.main

import com.codingwithmitch.daggerpractice.network.main.MainApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object MainModule {
    @JvmStatic
    @Provides
    fun provideMainApi(retrofit: Retrofit): MainApi {
        return retrofit.create(MainApi::class.java)
    }
}