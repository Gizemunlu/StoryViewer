package com.example.storyviewer.data.network

import com.example.storyviewer.data.model.Post
import com.example.storyviewer.data.model.Story
import retrofit2.Response
import retrofit2.http.GET

interface PostApi {

    @GET("7f0dedbd/")
    suspend fun getPost(): Response<Post>

    @GET("7f0dedbd/")
    suspend fun getStories(): Response<Story>
}