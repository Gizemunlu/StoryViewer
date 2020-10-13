package com.example.storyviewer.data.repository

import com.example.storyviewer.data.network.PostApi

class PostRepository(val postApi: PostApi) {

    fun getAllPosts() = postApi.getPost()

}