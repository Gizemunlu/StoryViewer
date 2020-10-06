package com.example.storyviewer.module

import com.example.storyviewer.Constants.Companion.BASE_URL
import com.example.storyviewer.network.PostApi
import com.example.storyviewer.repository.PostRepository
import com.example.storyviewer.viewmodel.PostViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import org.koin.android.viewmodel.ext.koin.viewModel
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val mainModule = module{
    single      {   createWebService()          }
    single      {   PostRepository(get())      }
    viewModel   {   PostViewModel(get())       }
}

fun createWebService(): PostApi {

    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(BASE_URL)
        .client(client)
        .build()

    return retrofit.create(PostApi::class.java)
}