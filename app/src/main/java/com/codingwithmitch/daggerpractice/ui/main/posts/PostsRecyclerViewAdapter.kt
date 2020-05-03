package com.codingwithmitch.daggerpractice.ui.main.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codingwithmitch.daggerpractice.R
import com.codingwithmitch.daggerpractice.models.Post

class PostsRecyclerViewAdapter : RecyclerView.Adapter<PostViewHolder>() {
    var posts: List<Post> = listOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_post_list_item, parent, false)
        return PostViewHolder(view)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position])
    }
}