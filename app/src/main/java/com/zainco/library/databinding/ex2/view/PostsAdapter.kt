package com.zainco.library.databinding.ex2.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.zainco.library.R
import com.zainco.library.databinding.PostRowItemBinding
import com.zainco.library.databinding.ex2.model.Post


class PostsAdapter(
    val postList: List<Post>,
    var layoutInflater: LayoutInflater,
    val postsAdapterListener: PostsAdapterListener
) : RecyclerView.Adapter<PostsAdapter.MyViewHolder>() {

    interface PostsAdapterListener {
        fun onPostClicked(post: Post?)
    }

    class MyViewHolder(binding: PostRowItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = DataBindingUtil.inflate<PostRowItemBinding>(
            layoutInflater,
            R.layout.post_row_item,
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = postList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.setPost(postList[position])
        holder.binding.thumbnail.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                postsAdapterListener.onPostClicked(postList.get(position));
            }
        })
    }
}