package com.codingwithmitch

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import com.codingwithmitch.daggerpractice.SessionManager
import com.codingwithmitch.daggerpractice.models.User
import com.codingwithmitch.daggerpractice.ui.auth.AuthActivity
import com.codingwithmitch.daggerpractice.ui.auth.AuthStatus
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity() {
    companion object {
        private const val TAG = "BaseActivity"
    }
    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeObservers()
    }

    private fun subscribeObservers() {
        sessionManager.getAuthUser().observe(this, Observer<AuthStatus<User>> { status ->
            when (status) {
                is AuthStatus.Loading -> {}
                is AuthStatus.Authenticated -> {
                    Log.d(TAG, "subscribeObservers: ${status.data.email}")
                }
                is AuthStatus.Error -> {
                    Toast.makeText(this, "${status.msg}\nDid you enter a number between 1 and 10?", Toast.LENGTH_LONG).show()
                }
                is AuthStatus.NotAuthenticated -> {
                    navLoginScreen()
                }
            }
        })
    }

    private fun navLoginScreen() {
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        finish()
    }

}