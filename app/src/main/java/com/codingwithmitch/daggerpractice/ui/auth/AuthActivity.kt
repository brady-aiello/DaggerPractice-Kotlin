package com.codingwithmitch.daggerpractice.ui.auth

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import com.bumptech.glide.RequestManager
import com.codingwithmitch.daggerpractice.R
import com.codingwithmitch.daggerpractice.models.User
import com.codingwithmitch.daggerpractice.ui.main.MainActivity
import com.codingwithmitch.daggerpractice.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity(), View.OnClickListener {

    companion object {
        private const val TAG = "AuthActivity"
    }

    lateinit var userId: EditText
    lateinit var viewModel: AuthViewModel
    lateinit var progressBar: ProgressBar

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    @Inject
    lateinit var logo: Drawable

    @Inject
    lateinit var requestManager: RequestManager
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        progressBar = findViewById(R.id.progress_bar)
        userId = findViewById(R.id.user_id_input)
        findViewById<Button>(R.id.login_button).setOnClickListener(this)
        viewModel = providerFactory.create(AuthViewModel::class.java)
        setTheLogo()
        subscribeObservers()
    }

    private fun subscribeObservers() : Unit {
        viewModel.observeAuthState().observe(this, Observer<AuthStatus<User>> { status ->
            when (status) {
                is AuthStatus.Loading -> showProgressBar(true)
                is AuthStatus.Authenticated -> {
                    showProgressBar(false)
                    Log.d(TAG, "subscribeObservers: ${status.data.email}")
                    onLoginSuccess()
                }
                is AuthStatus.Error -> {
                    showProgressBar(false)
                    Toast.makeText(this, "${status.msg}\nDid you enter a number between 1 and 10?", Toast.LENGTH_LONG).show()
                }
                is AuthStatus.NotAuthenticated -> {
                    showProgressBar(false)
                }
            }
        })
    }

    private fun onLoginSuccess() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun showProgressBar(isVisible: Boolean) {
        progressBar.visibility = if (isVisible) ProgressBar.VISIBLE else ProgressBar.GONE
    }

    private fun setTheLogo() : Unit {
        requestManager
            .load(logo)
            .into(findViewById(R.id.login_logo))
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.login_button -> {
                attemptLogin()
            }
        }
    }

    private fun attemptLogin() {
        if (TextUtils.isEmpty(userId.text.toString())) {
            return
        } else {
            val id = Integer.parseInt(userId.text.toString())
            viewModel.authenticateWithId(id)
        }
    }
}