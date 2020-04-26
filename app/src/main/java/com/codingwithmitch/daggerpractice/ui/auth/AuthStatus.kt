package com.codingwithmitch.daggerpractice.ui.auth


sealed class AuthStatus<T> {
    class Authenticated<T>(val data: T) : AuthStatus<T>()
    class Error<T>(val msg: String, val data: T?): AuthStatus<T>()
    class Loading<T>(val data: T?): AuthStatus<T>()
    class NotAuthenticated<T>: AuthStatus<T>()
}