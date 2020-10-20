package com.example.storyviewer.di

import com.example.storyviewer.utils.Constants.Companion.BASE_URL
import com.example.storyviewer.data.network.PostApi
import com.example.storyviewer.data.repository.PostRepository
import com.example.storyviewer.viewmodel.HomeViewModel
import com.squareup.picasso.Picasso
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

val netModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
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