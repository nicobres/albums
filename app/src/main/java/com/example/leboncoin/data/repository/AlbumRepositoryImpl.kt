package com.example.leboncoin.data.repository

import com.example.leboncoin.data.Album
import com.example.leboncoin.data.AlbumDataMapper
import com.example.leboncoin.data.local.AppDatabase
import com.example.leboncoin.data.remote.ApiAdapter
import com.example.leboncoin.utils.RequestResult


class AlbumRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val apiAdapter: ApiAdapter
) : AlbumRepository {

    override suspend fun getRemoteAlbums(): RequestResult<List<Album>> {
        val response = apiAdapter.apiClient.getAlbums()
        return if (response.isSuccessful) {
            val albums =
                response.body()?.map { AlbumDataMapper.convertDtoToModel(it) } ?: emptyList()
            saveAlbums(albums)
            RequestResult.Success(albums)
        } else
            RequestResult.Error(response.message())
    }

    override fun getLocalAlbums(): RequestResult<List<Album>> {
        val albums = appDatabase.albumDao().getAll()
            ?.map { AlbumDataMapper.convertEntityToModel(it) }

        if (albums != null && albums.isNotEmpty()) {
            return RequestResult.Success(albums)
        } else {
            return RequestResult.Error("No album in DB")
        }
    }

    override fun saveAlbums(albums: List<Album>) {
        appDatabase.albumDao().insertAll(
            *(albums.map { AlbumDataMapper.convertModelToEntity(it) }.toTypedArray())
        )
    }
}