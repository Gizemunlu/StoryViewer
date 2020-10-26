package com.example.storyviewer.data.model

import com.squareup.moshi.Json


data class PostItem(
    @field:Json(name = "title")val title: String,
    @field:Json(name = "description")val description: String,
    @field:Json(name = "thumb")val thumb: String
)