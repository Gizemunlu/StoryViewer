package com.example.storyviewer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.delegateadapter.delegate.diff.IComparableItem
import com.example.storyviewer.data.model.Post
import com.example.storyviewer.data.model.Story
import com.example.storyviewer.utils.Event
import com.example.storyviewer.ui.adapter.post.PostItemViewModel
import com.example.storyviewer.data.repository.PostRepository
import com.example.storyviewer.ui.adapter.StoryListViewModel
import com.example.storyviewer.ui.adapter.story.StoryItemViewModel
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel (private val repository: PostRepository) : ViewModel() {

    private val _state = MutableLiveData<ViewState>()
    val state: LiveData<ViewState> get() = _state


    private val _event = MutableLiveData<Event<ViewEvent>>()
    val event: LiveData<Event<ViewEvent>> get() = _event


    private val _contentList = MutableLiveData<List<IComparableItem>>()
    val contentList: LiveData<List<IComparableItem>> get() = _contentList

    init {
        loadContent()
    }

    private fun loadContent() {
        _state.value = ViewState.LOADING
        viewModelScope.launch(Default) {
            try {
                _contentList.postValue(
                    prepareListOfData(
                        repository.getAllPosts(),
                        repository.getAllStories()
                    )
                )
                _state.postValue(ViewState.DEFAULT)
            }catch (e:Exception){
                _event.postValue(Event(ViewEvent.ERROR))
                delay(4000)
                withContext(Main) { loadContent()  }
            }
        }

    }

    private fun prepareListOfData(
        listPosts: List<Post>,
        listStories: List<Story>
    ) =
        mutableListOf<IComparableItem>().apply {
            addAll(listPosts.map {
                PostItemViewModel(it)
            }.shuffled())
            add(0, StoryListViewModel(
                mutableListOf<IComparableItem>().apply {
                    addAll(listStories.map {
                        StoryItemViewModel(it)
                    }.shuffled())
                }
            )
            )
        }

    enum class ViewState {
        LOADING,
        DEFAULT
    }

    enum class ViewEvent {
        ERROR
    }
}
