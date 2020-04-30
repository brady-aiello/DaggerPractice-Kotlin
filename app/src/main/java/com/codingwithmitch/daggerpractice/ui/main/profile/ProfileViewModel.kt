package com.codingwithmitch.daggerpractice.ui.main.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.codingwithmitch.daggerpractice.SessionManager
import com.codingwithmitch.daggerpractice.models.User
import com.codingwithmitch.daggerpractice.ui.auth.AuthStatus
import javax.inject.Inject

class ProfileViewModel @Inject constructor(private val sessionManager: SessionManager): ViewModel() {
    companion object {
        private const val TAG = "ProfileViewModel"
    }

    init {
        Log.d(TAG, ": viewmodel is ready ")
    }

    fun getAuthenticatedUser(): LiveData<AuthStatus<User>> {
        return sessionManager.getAuthUser()
    }
}