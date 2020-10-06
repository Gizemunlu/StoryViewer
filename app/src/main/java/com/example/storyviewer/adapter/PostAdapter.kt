package com.example.storyviewer.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.storyviewer.R
import com.example.storyviewer.model.Post

class PostAdapter(private val postList: List<Post>): RecyclerView.Adapter<PostAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post,parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return postList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val post = postList[position]

        holder.title.text = post.title
        holder.description.text = post.description

        Glide.with(holder.imageProfile.context)
            .load(post.thumb)
            .into(holder.imageProfile)
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        val title = itemView.findViewById<TextView>(R.id.title)
        val description = itemView.findViewById<TextView>(R.id.description)
        val imageProfile = itemView.findViewById<ImageView>(R.id.image_profile)

    }

}