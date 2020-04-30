package com.codingwithmitch.daggerpractice.ui.main.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import com.codingwithmitch.daggerpractice.R
import com.codingwithmitch.daggerpractice.models.User
import com.codingwithmitch.daggerpractice.ui.auth.AuthStatus
import com.codingwithmitch.daggerpractice.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ProfileFragment : DaggerFragment() {
    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    lateinit var viewModel: ProfileViewModel
    private lateinit var email: TextView
    private lateinit var userName: TextView
    private lateinit var website: TextView

    companion object {
        private const val TAG = "ProfileFragment"
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated: ProfileFragment was created...")
        viewModel = providerFactory.create(ProfileViewModel::class.java)
        email = view.findViewById(R.id.email)
        userName = view.findViewById(R.id.username)
        website = view.findViewById(R.id.website)
        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.getAuthenticatedUser().removeObservers(viewLifecycleOwner)
        viewModel.getAuthenticatedUser().observe(viewLifecycleOwner, Observer<AuthStatus<User>> { status ->
            if (status != null ) {
                when (status) {
                    is AuthStatus.Authenticated -> {
                        Log.d(TAG, "subscribeObservers: setting user details")
                        setUserDetails(status.data)
                    }
                    is AuthStatus.Error -> {
                        Log.d(TAG, "subscribeObservers: setting error")
                        setErrorDetails(status.msg)
                    }
                    is AuthStatus.NotAuthenticated -> {
                        Log.d(TAG, "subscribeObservers: not authenticated")
                    }
                    is AuthStatus.Loading -> {
                        Log.d(TAG, "subscribeObservers: loading")
                    }
                }
            }
        })
    }

    private fun setErrorDetails(msg: String) {
        email.text = msg
        userName.text = "error"
        website.text = "error"
    }

    private fun setUserDetails(user: User) {
        email.text = user.email
        userName.text = user.username
        website.text = user.website
    }
}