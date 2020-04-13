package com.codingwithmitch.daggerpractice.ui.auth

import android.util.Log
import androidx.lifecycle.*
import com.codingwithmitch.daggerpractice.models.User
import com.codingwithmitch.daggerpractice.network.auth.AuthApi
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthViewModel @Inject constructor(private val authApi: AuthApi) : ViewModel() {

    private val authUser = MediatorLiveData<User>()
    companion object {
        private const val TAG = "AuthViewModel"
    }

    init {
        Log.d(TAG, "AuthViewModel: viewmodel is working...")

    }

    fun observeUser(): LiveData<User> {
        return authUser
    }

    fun authenticateWithId(userId: Int): Unit {
        val source: LiveData<User> = LiveDataReactiveStreams.fromPublisher(
            authApi.getUser(userId)
                .subscribeOn(Schedulers.io())
        )
        authUser.addSource(source) { user ->
            authUser.value = user
            authUser.removeSource(source)
         }
    }
}