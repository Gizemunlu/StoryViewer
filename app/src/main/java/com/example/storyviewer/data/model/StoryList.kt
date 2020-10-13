package com.example.storyviewer.data.model

import com.example.storyviewer.data.model.Story
import java.io.Serializable

data class StoryList(
    val sources: List<Story>
):Serializable