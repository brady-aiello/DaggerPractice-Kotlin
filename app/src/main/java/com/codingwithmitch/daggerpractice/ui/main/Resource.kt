package com.codingwithmitch.daggerpractice.ui.main

sealed class Resource<T> {
    class Success<T>(val data: T) : Resource<T>()
    class Error<T>(val msg: String, val data: T?): Resource<T>()
    class Loading<T>(val data: T?): Resource<T>()
}