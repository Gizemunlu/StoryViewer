package com.example.storyviewer.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.storyviewer.model.Post
import com.example.storyviewer.model.PostList
import com.example.storyviewer.repository.PostRepository
import org.koin.standalone.KoinComponent

class PostViewModel(val postRepository: PostRepository) : ViewModel(), KoinComponent  {

    var listofPost = MutableLiveData<List<Post>>()

    init {
        listofPost.value =  listOf()
    }

    fun getPost(){
        postRepository.getPost(object: PostRepository.OnPostData{
            override fun onSuccess(postList: PostList){
                listofPost.value= postList.videos
            }

            override fun onFail(){
                listofPost.value= listOf()
            }
        })
    }
}