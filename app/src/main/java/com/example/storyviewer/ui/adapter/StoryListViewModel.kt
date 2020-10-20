package com.example.storyviewer.ui.adapter

import com.example.delegateadapter.delegate.diff.IComparableItem

class StoryListViewModel(val list: List<IComparableItem>) :
        IComparableItem {

    override fun id(): Any = StoryListViewModel::class.toString()

    override fun content() = list

}