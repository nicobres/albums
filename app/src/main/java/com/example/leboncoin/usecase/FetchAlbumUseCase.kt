package com.example.leboncoin.usecase

import com.example.leboncoin.data.Album
import com.example.leboncoin.data.repository.AlbumRepository
import com.example.leboncoin.utils.NetworkHelper
import com.example.utils.RequestResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FetchAlbumUseCase(
    private val networkHelper: NetworkHelper,
    private val albumRepository: AlbumRepository
) {

    suspend fun execute() : RequestResult<List<Album>> {
        val result = withContext(Dispatchers.IO) {
            if (networkHelper.isNetworkAvailable()) {
                val remoteAlbumResult = albumRepository.getRemoteAlbums()
                if (remoteAlbumResult is RequestResult.Success) {
                    return@withContext remoteAlbumResult
                } else {
                    return@withContext albumRepository.getLocalAlbums()
                }
            } else {
                return@withContext albumRepository.getLocalAlbums()
            }
        }
        return result
    }
}

