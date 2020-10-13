package com.example.storyviewer.ui.adapter

import com.example.delegateadapter.delegate.diff.IComparableItem
import com.example.storyviewer.data.model.Post

class PostItemViewModel (val post: Post) :
    IComparableItem {

    override fun id(): Any = PostItemViewModel::class.toString()

    override fun content() = post
}