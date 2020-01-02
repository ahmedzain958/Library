package com.zainco.library.databinding.ex2.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.zainco.library.R
import com.zainco.library.databinding.PostRowItemBinding
import com.zainco.library.databinding.ex2.model.Post


class PostsAdapter(
    val postList: List<Post>,
    val postsAdapterListener: PostsAdapterListener
) : RecyclerView.Adapter<PostsAdapter.MyViewHolder>() {

    interface PostsAdapterListener {
        fun onPostClicked(post: Post?)
    }

    class MyViewHolder(val binding: PostRowItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = DataBindingUtil.inflate<PostRowItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.post_row_item,
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = postList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.post = postList[position]
        holder.binding.thumbnail.setOnClickListener {
            postsAdapterListener.onPostClicked(postList[position]);
        }
    }
}