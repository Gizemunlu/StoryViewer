package com.example.storyviewer.model

import java.io.Serializable

data class Post(
    var title: String,
    var description: String,
    var thumb: String
):Serializable