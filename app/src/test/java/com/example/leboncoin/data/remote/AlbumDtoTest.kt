package com.example.leboncoin.data.remote

import com.example.leboncoin.data.Album
import com.google.gson.GsonBuilder
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.BufferedReader
import java.io.InputStreamReader

class AlbumDtoTest {

    @Test
    fun readJsonAlbumDtoTest() {
        // Arrange
        val gson = GsonBuilder().create()

        val json  = readFile("album.json")

        // Act
        val album: Album = gson.fromJson(json, Album::class.java)
        // Assert
        assertEquals(1, album.id)
        assertEquals("accusamus beatae ad facilis cum similique qui sunt", album.title)
        assertEquals("https://via.placeholder.com/150/92c952", album.thumbnailUrl)
    }


    private fun readFile(filePath: String): String {
        val bufferedReader =
            BufferedReader(InputStreamReader(javaClass.classLoader?.getResourceAsStream(filePath)))
        val stringBuilder = StringBuilder()
        var line = bufferedReader.readLine()
        while (line != null) {
            stringBuilder.append(line)
            line = bufferedReader.readLine()
        }
        return stringBuilder.toString()
    }
}