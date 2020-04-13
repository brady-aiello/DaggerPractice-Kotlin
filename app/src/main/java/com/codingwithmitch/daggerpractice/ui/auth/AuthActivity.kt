package com.codingwithmitch.daggerpractice.ui.auth

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import com.bumptech.glide.RequestManager
import com.codingwithmitch.daggerpractice.R
import com.codingwithmitch.daggerpractice.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity(), View.OnClickListener {

    companion object {
        private const val TAG = "AuthActivity"
    }

    lateinit var userId: EditText
    lateinit var viewModel: AuthViewModel

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    @Inject
    lateinit var logo: Drawable

    @Inject
    lateinit var requestManager: RequestManager
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        userId = findViewById(R.id.user_id_input)
        findViewById<Button>(R.id.login_button).setOnClickListener(this)
        viewModel = providerFactory.create(AuthViewModel::class.java)
        //viewModel = ViewModelProviders.of(this, providerFactory).get(AuthViewModel::class.java)
        setTheLogo()
        subscribeObservers()
    }

    private fun subscribeObservers() : Unit {
        viewModel.observeUser().observe(this, Observer { user ->
            Log.d(TAG, "subscribeObservers: ${user.email}")
        })
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