package com.example.storyviewer.network

import com.example.storyviewer.model.StoryList
import retrofit2.Call
import retrofit2.http.GET

interface StoryApi {
    @GET("7f0dedbd/")
    fun getStories(): Call<StoryList>
}