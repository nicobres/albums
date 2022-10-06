package com.example.leboncoin.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface ApiClient {

    @GET("/img/shared/technical-test.json")
    suspend fun getAlbums(): Response<List<AlbumDto>>
}