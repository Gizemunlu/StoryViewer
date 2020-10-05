package com.example.storyviewer.repository

import com.example.storyviewer.model.StoryList
import com.example.storyviewer.network.StoryApi
import retrofit2.Call
import retrofit2.Response

class StoryRepository(val storyApi: StoryApi) {

    fun getStories(onStoriesData: OnStoriesData){
        storyApi.getStories().enqueue(object : retrofit2.Callback<StoryList> {
            override fun onResponse(call: Call<StoryList>, response: Response<StoryList>) {
               onStoriesData.onSuccess((response.body() as StoryList))
            }
            override fun onFailure(call: Call<StoryList>, t: Throwable) {
                onStoriesData.onFail()
            }

        })
    }

    interface OnStoriesData {
        fun onSuccess(storyList: StoryList)
        fun onFail()
    }
}