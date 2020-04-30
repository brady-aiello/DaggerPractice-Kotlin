package com.codingwithmitch.daggerpractice

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.codingwithmitch.daggerpractice.models.User
import com.codingwithmitch.daggerpractice.ui.auth.AuthStatus
import javax.inject.Inject

class SessionManager @Inject constructor() {
    companion object {
        private const val TAG = "SessionManager"
    }
    private val cachedUser : MediatorLiveData<AuthStatus<User>> = MediatorLiveData()

    public fun authenticateWithId(source: LiveData<AuthStatus<User>>) {
        cachedUser.value = AuthStatus.Loading(null)
        cachedUser.addSource(source) {
            cachedUser.value = it
            cachedUser.removeSource(source)
        }
    }

    public fun logOut() {
        Log.d(TAG, "logOut: logging out...")
        cachedUser.value = AuthStatus.NotAuthenticated()
    }

    public fun getAuthUser() : LiveData<AuthStatus<User>> {
        return cachedUser;
    }
}