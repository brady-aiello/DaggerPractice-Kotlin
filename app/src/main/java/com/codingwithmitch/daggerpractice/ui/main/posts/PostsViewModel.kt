package com.codingwithmitch.daggerpractice.ui.main.posts

import androidx.lifecycle.ViewModel
import com.codingwithmitch.daggerpractice.SessionManager
import com.codingwithmitch.daggerpractice.network.main.MainApi
import javax.inject.Inject

class PostsViewModel @Inject constructor(val sessionManager: SessionManager,
                                         val mainApi: MainApi) : ViewModel() {
}