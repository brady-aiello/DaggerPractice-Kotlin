package com.codingwithmitch.daggerpractice.ui.main.posts

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codingwithmitch.daggerpractice.R
import com.codingwithmitch.daggerpractice.models.Post

class PostViewHolder(val item: View): RecyclerView.ViewHolder(item) {
    private var titleTextView: TextView = this.item.findViewById(R.id.title)

    fun bind(post: Post) {
        titleTextView.text = post.title
    }
}