package com.example.leboncoin.data.repository

import com.example.leboncoin.data.Album
import com.example.utils.RequestResult

interface AlbumRepository {
    suspend fun getRemoteAlbums(): RequestResult<List<Album>>
    fun getLocalAlbums(): RequestResult<List<Album>>
    fun saveAlbums(albums: List<Album>)
}