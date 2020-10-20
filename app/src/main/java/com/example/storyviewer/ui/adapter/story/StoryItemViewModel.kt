package com.example.storyviewer.ui.adapter.story

import com.example.delegateadapter.delegate.diff.IComparableItem
import com.example.storyviewer.data.model.Story

class StoryItemViewModel(val story: Story) :
    IComparableItem {

    override fun id(): Any  = StoryItemViewModel::class.toString()

    override fun content() = story
}