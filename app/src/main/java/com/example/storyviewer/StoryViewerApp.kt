package com.example.storyviewer

import android.app.Application
import com.example.storyviewer.module.mainModule
import org.koin.android.ext.android.startKoin

class StoryViewerApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(mainModule), loadPropertiesFromFile = true)
    }
}