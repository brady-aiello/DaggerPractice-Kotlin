package com.codingwithmitch.daggerpractice.ui.auth

import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.codingwithmitch.daggerpractice.models.User
import com.codingwithmitch.daggerpractice.network.auth.AuthApi
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

public class AuthViewModel @Inject constructor(private val authApi: AuthApi) : ViewModel() {

    companion object {
        private const val TAG = "AuthViewModel"
    }

    init {
        Log.d(TAG, "AuthViewModel: viewmodel is working...")
        authApi.getUser(1)
            .toObservable()
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onNext = {
                    Log.d(TAG, "`onNext: ${it.email}")
                },
                onError = {
                    Log.e(TAG, "onError: ${it.toString()}")
                },
                onComplete = {

                }
            )
    }
}