package com.example.storyviewer.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.storyviewer.R
import com.example.storyviewer.model.Story

class StoryAdapter(private val storyList: List<Story>): RecyclerView.Adapter<StoryAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_story,parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return storyList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val story = storyList[position]

        holder.title.text = story.title
        holder.description.text = story.description

        Glide.with(holder.imageProfile.context)
            .load(story.thumb)
            .into(holder.imageProfile)
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        val title = itemView.findViewById<TextView>(R.id.title)
        val description = itemView.findViewById<TextView>(R.id.description)
        val imageProfile = itemView.findViewById<ImageView>(R.id.image_profile)

    }

}