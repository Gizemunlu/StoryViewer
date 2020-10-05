package com.example.storyviewer.model

import java.io.Serializable

data class Story(
    var title: String,
    var description: String,
    var thumb: String
):Serializable