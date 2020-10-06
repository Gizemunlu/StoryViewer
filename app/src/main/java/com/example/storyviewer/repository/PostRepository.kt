package com.example.storyviewer.repository

import com.example.storyviewer.model.PostList
import com.example.storyviewer.network.PostApi
import retrofit2.Call
import retrofit2.Response

class PostRepository(val postApi: PostApi) {

    fun getPost(onPostData: OnPostData){
        postApi.getPost().enqueue(object : retrofit2.Callback<PostList> {
            override fun onResponse(call: Call<PostList>, response: Response<PostList>) {
               onPostData.onSuccess((response.body() as PostList))
            }
            override fun onFailure(call: Call<PostList>, t: Throwable) {
                onPostData.onFail()
            }

        })
    }

    interface OnPostData {
        fun onSuccess(postList: PostList)
        fun onFail()
    }
}