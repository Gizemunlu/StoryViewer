package com.example.storyviewer.data.model

import com.squareup.moshi.Json
import java.io.Serializable

data class StoryItem(
    @field:Json(name = "thumb")val thumb: String
    //@field:Json(name = "sources")val sources: String
): Serializable