package com.codingwithmitch.daggerpractice.ui.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import com.codingwithmitch.daggerpractice.network.auth.AuthApi
import javax.inject.Inject

public class AuthViewModel @Inject constructor(private val authApi: AuthApi) : ViewModel() {

    companion object {
        private const val TAG = "AuthViewModel"
    }

    init {
        Log.d(TAG, "AuthViewModel: viewmodel is working...")
    }
}