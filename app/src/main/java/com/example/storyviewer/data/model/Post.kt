package com.example.storyviewer.data.model

import com.squareup.moshi.Json


data class Post(
    @field:Json(name = "videos") val videos: List<PostItem>?
)