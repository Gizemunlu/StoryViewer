package com.example.storyviewer.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.storyviewer.model.Story
import com.example.storyviewer.model.StoryList
import com.example.storyviewer.repository.StoryRepository
import org.koin.standalone.KoinComponent

class StoryViewModel(val storyRepository: StoryRepository) : ViewModel(), KoinComponent  {

    var listofStories = MutableLiveData<List<Story>>()

    init {
        listofStories.value =  listOf()
    }

    fun getStories(){
        storyRepository.getStories(object: StoryRepository.OnStoriesData{
            override fun onSuccess(storyList: StoryList){
                listofStories.value= storyList.videos
            }

            override fun onFail(){
                listofStories.value= listOf()
            }
        })
    }
}