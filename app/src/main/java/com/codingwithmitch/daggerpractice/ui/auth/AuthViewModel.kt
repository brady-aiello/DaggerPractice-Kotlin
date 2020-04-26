package com.codingwithmitch.daggerpractice.ui.auth

import android.util.Log
import androidx.lifecycle.*
import com.codingwithmitch.daggerpractice.models.User
import com.codingwithmitch.daggerpractice.network.auth.AuthApi
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthViewModel @Inject constructor(private val authApi: AuthApi) : ViewModel() {

    private val authUser = MediatorLiveData<AuthStatus<User>>()
    companion object {
        private const val TAG = "AuthViewModel"
    }

    init {
        Log.d(TAG, "AuthViewModel: viewmodel is working...")

    }

    fun observeUser(): LiveData<AuthStatus<User>> {
        return authUser
    }

    fun authenticateWithId(userId: Int): Unit {
        authUser.value = AuthStatus.Loading<User>(null)
        val source: LiveData<AuthStatus<User>> = LiveDataReactiveStreams.fromPublisher(
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
        authUser.addSource(source) { user ->
            authUser.value = user
            authUser.removeSource(source)
         }
    }
}