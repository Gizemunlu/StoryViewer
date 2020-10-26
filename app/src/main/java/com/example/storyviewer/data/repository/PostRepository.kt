package com.example.storyviewer.data.repository

import com.example.storyviewer.data.model.PostItem
import com.example.storyviewer.data.model.StoryItem
import com.example.storyviewer.data.network.PostApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostRepository(val postApi: PostApi) {

    private val post: MutableList<PostItem> = mutableListOf()
    private val story: MutableList<StoryItem> = mutableListOf()

    suspend fun getAllPosts(): List<PostItem> {
        val response = postApi.getPost()
        if (response.isSuccessful) {
            val list = response.body()?.videos
            list?.let {
                post.addAll(it)
            }
        }
        return post
    }

    suspend fun getAllStories(): List<StoryItem> {
        val response = postApi.getStories()
        if (response.isSuccessful) {
            val list = response.body()?.videos
            list?.let {
                story.addAll(it)
            }
        }
        return story
    }
}