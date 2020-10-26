package com.example.storyviewer.data.model

import com.squareup.moshi.Json



data class StoryItem(
    @field:Json(name = "thumb")val thumb: String
    //@field:Json(name = "sources")val sources: String
)