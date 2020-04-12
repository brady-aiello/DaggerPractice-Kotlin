package com.codingwithmitch.daggerpractice.network.auth

import com.codingwithmitch.daggerpractice.models.User
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path


interface AuthApi {
    @GET("users/{id}")
    fun getUser(@Path("id") id: Int): Flowable<User>
}