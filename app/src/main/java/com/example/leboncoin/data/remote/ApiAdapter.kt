package com.example.leboncoin.data.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ApiAdapter {
    val apiClient: ApiClient = Retrofit.Builder()
        .baseUrl("https://static.leboncoin.fr")
        .client(OkHttpClient())
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(ApiClient::class.java)
}