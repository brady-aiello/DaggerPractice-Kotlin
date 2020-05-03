package com.codingwithmitch.daggerpractice.ui.main.posts

import android.util.Log
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.codingwithmitch.daggerpractice.SessionManager
import com.codingwithmitch.daggerpractice.models.Post
import com.codingwithmitch.daggerpractice.network.main.MainApi
import com.codingwithmitch.daggerpractice.ui.auth.AuthStatus
import com.codingwithmitch.daggerpractice.ui.main.Resource
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostsViewModel @Inject constructor(val sessionManager: SessionManager,
                                         val mainApi: MainApi) : ViewModel() {

    private val posts: MediatorLiveData<Resource<List<Post>>> = MediatorLiveData()
    companion object {
        private const val TAG = "PostsViewModel"
    }
    init {
        posts.value = Resource.Loading(null)
    }


    fun observePosts() : MediatorLiveData<Resource<List<Post>>>? {
        val authStatus = sessionManager.getAuthUser().value
        if(authStatus is AuthStatus.Authenticated) {
            val source = LiveDataReactiveStreams.fromPublisher(
                    mainApi.getPostsFromUser(authStatus.data.id)
                            .onErrorReturn {
                                Log.e(TAG, "observePosts", it)
                                val post = Post(-1, -1, "", "")
                                listOf(post)
                            }
                            .map {
                                if (it.isNotEmpty() && it[0].id != -1) {
                                        Resource.Success(it)
                                } else {
                                    Resource.Error<List<Post>>("Something went wrong", null)
                                }
                            }
                            .subscribeOn(Schedulers.io())
            )
            posts.addSource(source, Observer {
                posts.value = it
                posts.removeSource(source)
            })
            return posts
        } else {
            val error = MediatorLiveData<Resource<List<Post>>>()
            error.value = Resource.Error("Something went wrong", null)
            return error
        }
    }
}