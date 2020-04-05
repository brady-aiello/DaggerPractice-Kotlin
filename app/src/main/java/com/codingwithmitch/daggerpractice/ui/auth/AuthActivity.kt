package com.codingwithmitch.daggerpractice.ui.auth

import android.graphics.drawable.Drawable
import android.os.Bundle
import com.bumptech.glide.RequestManager
import com.codingwithmitch.daggerpractice.R
import com.codingwithmitch.daggerpractice.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity() {

    companion object {
        private const val TAG = "AuthActivity"
    }

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
        viewModel = providerFactory.create(AuthViewModel::class.java)
        //viewModel = ViewModelProviders.of(this, providerFactory).get(AuthViewModel::class.java)
        setTheLogo()
    }

    private fun setTheLogo() : Unit {
        requestManager
            .load(logo)
            .into(findViewById(R.id.login_logo))
    }
}