package com.example.storyviewer.data.model

import com.squareup.moshi.Json
import java.io.Serializable


data class Story(
    @field:Json(name = "videos") val videos: List<StoryItem>?
):Serializable