package com.example.storyviewer.data.network

import com.example.storyviewer.data.model.Post
import com.example.storyviewer.data.model.PostList
import com.example.storyviewer.data.model.StoryList
import retrofit2.Call
import retrofit2.http.GET

interface PostApi {
    @GET("7f0dedbd/")
    fun getPost(): List<Post>

    @GET("7f0dedbd/")
    fun getStories(): Call<StoryList>
}