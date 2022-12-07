package com.example.leboncoin.data.repository

import com.example.leboncoin.data.Album
import com.example.leboncoin.data.local.AlbumDao
import com.example.leboncoin.data.local.AlbumEntity
import com.example.leboncoin.data.local.AppDatabase
import com.example.leboncoin.data.remote.AlbumDto
import com.example.leboncoin.data.remote.ApiAdapter
import com.example.leboncoin.data.remote.ApiClient
import com.example.utils.RequestResult
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class AlbumRepositoryImplTest {

    private val db = mockk<AppDatabase>()
    private val albumDao = mockk<AlbumDao>(relaxed = true)

    private val apiAdapter = mockk<ApiAdapter>()
    private val apiClient = mockk<ApiClient>()

    private lateinit var repo : AlbumRepositoryImpl

    @Before
    fun setUp() {
        every { db.albumDao() } returns albumDao
        every { apiAdapter.apiClient } returns apiClient

        repo = AlbumRepositoryImpl(db, apiAdapter)
    }

    @Test
    fun getRemoteAlbums_Failed_Error() = runTest {
        // Arrange
        val response = mockk<Response<List<AlbumDto>>>()
        every { response.isSuccessful } returns false
        every { response.message() } returns "error"
        coEvery { apiClient.getAlbums() } returns response

        // Act
        val res = repo.getRemoteAlbums()

        // Assert
        Assert.assertEquals(
            RequestResult.Error("error"),
            res
        )
    }

    @Test
    fun getRemoteAlbums_Success_Success() = runTest {
        // Arrange
        val albums = listOf(
            AlbumDto(
                id = 1,
                title = "title",
                thumbnailUrl = "thumb"
            )
        )
        val response = mockk<Response<List<AlbumDto>>>()
        every { response.isSuccessful } returns true
        every { response.body() } returns albums
        coEvery { apiClient.getAlbums() } returns response

        // Act
        val res = repo.getRemoteAlbums()

        // Assert
        Assert.assertEquals(
            RequestResult.Success(listOf(Album(1,"title", "thumb"))),
            res
        )
    }

    @Test
    fun getLocalAlbums_Success() {
        // Arrange
        every { albumDao.getAll() } returns
                listOf(AlbumEntity(1,"title", "thumb"))

        // Act
        val res = repo.getLocalAlbums()

        // Assert
        Assert.assertEquals(
            res,
            RequestResult.Success(listOf(Album(1,"title","thumb")))
        )
    }

    @Test
    fun getLocalAlbums_Null_Error() {
        // Arrange
        every { albumDao.getAll() } returns null

        // Act
        val res = repo.getLocalAlbums()

        // Assert
        Assert.assertEquals(
            res,
            RequestResult.Error("No album in DB")
        )
    }

    @Test
    fun getLocalAlbums_EmptyList_Error() {
        // Arrange
        every { albumDao.getAll() } returns emptyList()

        // Act
        val res = repo.getLocalAlbums()

        // Assert
        Assert.assertEquals(
            res,
            RequestResult.Error("No album in DB")
        )
    }

    @Test
    fun saveAlbums() {
        // Arrange
        val albums = listOf(
            Album(1,"one","thumbOne"),
            Album(2,"two","thumbTwo"),
            Album(3,"three","thumbThree"),
        )

        // Act
        repo.saveAlbums(albums)

        // Assert
        verify { albumDao.insertAll(
            AlbumEntity(1,"one","thumbOne"),
            AlbumEntity(2,"two","thumbTwo"),
            AlbumEntity(3,"three","thumbThree"),
        ) }
    }
}