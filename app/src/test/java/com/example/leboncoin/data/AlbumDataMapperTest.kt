package com.example.leboncoin.data

import com.example.leboncoin.data.local.AlbumEntity
import com.example.leboncoin.data.remote.AlbumDto
import org.junit.Assert
import org.junit.Test

internal class AlbumDataMapperTest {

    @Test
    fun testConvertDtoToModel() {
        // Arrange
        val albumDto = AlbumDto(
            id = 1,
            title = "Mauvais ordre",
            thumbnailUrl = "https://thumbnail"
        )

        // Act
        val res = AlbumDataMapper.convertDtoToModel(albumDto)

        // Assert
        Assert.assertEquals(1, res.id)
        Assert.assertEquals("Mauvais ordre", res.title)
        Assert.assertEquals("https://thumbnail", res.thumbnailUrl)
    }

    @Test
    fun testConvertModelToEntity() {
        // Arrange
        val album = Album(
            id = 1,
            title = "Mauvais ordre",
            thumbnailUrl = "https://thumbnail"
        )

        // Act
        val res = AlbumDataMapper.convertModelToEntity(album)

        // Assert
        Assert.assertEquals(1, res.id)
        Assert.assertEquals("Mauvais ordre", res.title)
        Assert.assertEquals("https://thumbnail", res.thumbnailUrl)
    }

    @Test
    fun testConvertEntityToModel() {
        // Arrange
        val albumEntity = AlbumEntity(
            id = 1,
            title = "Mauvais ordre",
            thumbnailUrl = "https://thumbnail"
        )

        // Act
        val res = AlbumDataMapper.convertEntityToModel(albumEntity)

        // Assert
        Assert.assertEquals(1, res.id)
        Assert.assertEquals("Mauvais ordre", res.title)
        Assert.assertEquals("https://thumbnail", res.thumbnailUrl)
    }
}