package com.example.storyviewer.network

import com.example.storyviewer.model.PostList
import com.example.storyviewer.model.StoryList
import retrofit2.Call
import retrofit2.http.GET

interface PostApi {
    @GET("7f0dedbd/")
    fun getPost(): Call<PostList>

    @GET("7f0dedbd/")
    fun getStories(): Call<StoryList>
}