package com.example.storyviewer.di

import com.example.storyviewer.utils.Constants.Companion.BASE_URL
import com.example.storyviewer.data.network.PostApi
import com.example.storyviewer.data.repository.PostRepository
import com.example.storyviewer.viewmodel.HomeViewModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.picasso.Picasso
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val netModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }
    single { Picasso.Builder(androidContext()).build() }
}

val apiModule = module {
    single { get<Retrofit>().create(PostApi::class.java) }
}

val repositoryModule = module {
    single { PostRepository(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
}