package com.example.storyviewer.data.model

import com.squareup.moshi.Json



data class Story(
    @field:Json(name = "videos") val videos: List<StoryItem>?
)