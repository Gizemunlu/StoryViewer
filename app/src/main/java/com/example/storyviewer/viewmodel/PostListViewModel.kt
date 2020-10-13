package com.example.storyviewer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.delegateadapter.delegate.diff.IComparableItem
import com.example.storyviewer.utils.Event
import com.example.storyviewer.ui.adapter.PostItemViewModel
import com.example.storyviewer.data.model.Post
import com.example.storyviewer.data.repository.PostRepository
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PostListViewModel (private val repository: PostRepository) : ViewModel() {

    private val _state = MutableLiveData<ViewState>()
    val state: LiveData<ViewState> get() = _state

    enum class ViewState {
        LOADING,
        DEFAULT
    }


    private val _event = MutableLiveData<Event<ViewEvent>>()
    val event: LiveData<Event<ViewEvent>> get() = _event

    enum class ViewEvent {
        ERROR
    }

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
                        repository.getAllPosts()
                    )
                )
                _state.postValue(ViewState.DEFAULT)
            }catch (e:Exception){
                _event.postValue(Event(ViewEvent.ERROR))
                delay(4000)
                withContext(Main){loadContent()}
            }
        }

    }

    private fun prepareListOfData(
        listPosts: List<Post>
    ) =
        mutableListOf<IComparableItem>().apply {
            addAll(listPosts.map {
                PostItemViewModel(it)
            }.shuffled())
        }
}
