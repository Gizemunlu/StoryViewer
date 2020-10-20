package com.example.storyviewer.data.repository

import com.example.storyviewer.data.network.PostApi

class PostRepository(private val postApi: PostApi) {

   suspend fun getAllPosts() = postApi.getPost()

   suspend fun getAllStories() = postApi.getStories()

}