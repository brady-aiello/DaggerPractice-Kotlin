package com.codingwithmitch.daggerpractice.ui.auth

import android.util.Log
import androidx.lifecycle.*
import com.codingwithmitch.daggerpractice.SessionManager
import com.codingwithmitch.daggerpractice.models.User
import com.codingwithmitch.daggerpractice.network.auth.AuthApi
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthViewModel @Inject constructor(private val authApi: AuthApi,
                                        private val sessionManager: SessionManager) : ViewModel() {
    companion object {
        private const val TAG = "AuthViewModel"
    }

    init {
        Log.d(TAG, "AuthViewModel: viewmodel is working...")

    }

    fun observeAuthState(): LiveData<AuthStatus<User>> {
        return sessionManager.getAuthUser()
    }

    private fun queryUserId(userId: Int) : LiveData<AuthStatus<User>> {
        //authUser.value = AuthStatus.Loading<User>(null)
        return LiveDataReactiveStreams.fromPublisher(
            authApi.getUser(userId)
                .onErrorReturn { User("error@email.com", -1, "Error McErrorFace", "error.com") }
                .map { user ->
                    if (user.id == -1) {
                        AuthStatus.Error<User>("Could not authenticate", null)
                    } else {
                        AuthStatus.Authenticated<User>(user)
                    }
                }
                .subscribeOn(Schedulers.io())
        )
    }

    fun authenticateWithId(userId: Int): Unit {
        Log.d(TAG, "authenticateWithId: attempting to log in")
        sessionManager.authenticateWithId(queryUserId(userId))
    }
}