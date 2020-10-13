package com.example.storyviewer

import android.app.Application
import com.example.storyviewer.di.apiModule
import com.example.storyviewer.di.netModule
import com.example.storyviewer.di.repositoryModule
import com.example.storyviewer.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class StoryViewerApp : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@StoryViewerApp)
            modules(
                listOf(
                    viewModelModule,
                    repositoryModule,
                    apiModule,
                    netModule
                )
            )
        }
    }
}