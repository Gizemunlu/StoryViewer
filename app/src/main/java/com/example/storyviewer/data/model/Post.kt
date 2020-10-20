package com.example.storyviewer.data.model

import com.squareup.moshi.Json
import java.io.Serializable

data class Post(
    @field:Json(name = "videos") val videos: List<PostItem>?
) :Serializable