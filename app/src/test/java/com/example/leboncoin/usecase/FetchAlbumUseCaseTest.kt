package com.example.leboncoin.usecase

import com.example.leboncoin.data.Album
import com.example.leboncoin.data.repository.AlbumRepository
import com.example.leboncoin.utils.NetworkHelper
import com.example.utils.RequestResult
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class FetchAlbumUseCaseTest {

    private val networkHelper = mockk<NetworkHelper>()
    private val albumRepository = mockk<AlbumRepository>()

    private lateinit var fetchAlbumUseCase: FetchAlbumUseCase

    @Before
    fun setUp() {
        fetchAlbumUseCase = FetchAlbumUseCase(networkHelper, albumRepository)
    }

    @Test
    fun executeTest_NetworkNotAvailable_GetLocalAlbums() = runTest {
        // Arrange
        val successResult = RequestResult.Success(listOf(Album(1,"","")))
        every { networkHelper.isNetworkAvailable() } returns false
        every { albumRepository.getLocalAlbums() } returns successResult

        // Act

        val res = fetchAlbumUseCase.execute()

        // Assert
        Assert.assertEquals(successResult , res)
    }

    @Test
    fun executeTest_NetworkAvailableAndSuccess_GetRemoteAlbums() = runTest {
        // Arrange
        val successResult = RequestResult.Success(listOf(Album(1,"","")))
        every { networkHelper.isNetworkAvailable() } returns true
        coEvery { albumRepository.getRemoteAlbums() } returns successResult

        // Act
        val res = fetchAlbumUseCase.execute()

        // Assert
        Assert.assertEquals(successResult, res)
    }

    @Test
    fun executeTest_NetworkAvailableAndError_GetLocalAlbums() = runTest {
        // Arrange
        val errorResult = RequestResult.Error("")
        every { networkHelper.isNetworkAvailable() } returns true
        coEvery { albumRepository.getRemoteAlbums() } returns errorResult
        val successResult = RequestResult.Success(listOf(Album(1,"","")))
        every { albumRepository.getLocalAlbums() } returns successResult

        // Act
        val res = fetchAlbumUseCase.execute()

        // Assert
        Assert.assertEquals(successResult, res)
    }
}