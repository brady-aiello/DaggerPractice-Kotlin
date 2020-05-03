package com.codingwithmitch.daggerpractice.di.auth

import com.codingwithmitch.daggerpractice.models.User
import com.codingwithmitch.daggerpractice.network.auth.AuthApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
object AuthModule {
    @AuthScope
    @JvmStatic
    @Provides
    fun provideAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

    @AuthScope
    @JvmStatic
    @Provides
    @Named("auth_user")
    fun provideSomeUser(): User {
        return User("dummy email", 42, "imaginary user", "idontexist.com")
    }
}