package com.codingwithmitch.daggerpractice.ui.main.posts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codingwithmitch.daggerpractice.R
import com.codingwithmitch.daggerpractice.models.Post
import com.codingwithmitch.daggerpractice.ui.main.Resource
import com.codingwithmitch.daggerpractice.util.VerticalSpacingItemDecoration
import com.codingwithmitch.daggerpractice.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class PostsFragment : DaggerFragment() {
    companion object {
        private const val TAG = "PostsFragment"
    }
    private lateinit var viewModel: PostsViewModel
    private lateinit var recyclerView: RecyclerView

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory
    @Inject
    lateinit var postsRecyclerViewAdapter: PostsRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById(R.id.recycler_view)
        viewModel = viewModelProviderFactory.create(PostsViewModel::class.java)
        initRecyclerView()
        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.observePosts()?.removeObservers(viewLifecycleOwner)
        viewModel.observePosts()?.observe(viewLifecycleOwner, Observer<Resource<List<Post>>> {
            when(it) {
                is Resource.Loading<List<Post>> -> {
                    Log.d(TAG, "subscribeObservers: LOADING")
                }
                is Resource.Success<List<Post>> -> {
                    Log.d(TAG, "subscribeObservers: ${it.data}")
                    postsRecyclerViewAdapter.posts = it.data
                }
                is Resource.Error<List<Post>> -> {
                    Log.e(TAG, "subscribeObservers: ERROR... ${it.msg}")
                }
            }
        })
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val verticalSpacingItemDecoration = VerticalSpacingItemDecoration(15)
        recyclerView.addItemDecoration(verticalSpacingItemDecoration)
        recyclerView.adapter = postsRecyclerViewAdapter
    }
}