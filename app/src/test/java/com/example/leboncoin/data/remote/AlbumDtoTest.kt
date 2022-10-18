package com.example.leboncoin.data.remote

import com.example.leboncoin.data.Album
import com.squareup.moshi.Moshi
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.BufferedReader
import java.io.InputStreamReader

class AlbumDtoTest {

    @Test
    fun readJsonAlbumDtoTest() {
        // Arrange
        val moshi = Moshi.Builder().build()
        val adapter = moshi.adapter(Album::class.java)

        val json  = readFile("album.json")

        // Act
        val album: Album? = adapter.fromJson(json)

        // Assert
        assertEquals(1, album?.id)
        assertEquals("accusamus beatae ad facilis cum similique qui sunt", album?.title)
        assertEquals("https://via.placeholder.com/150/92c952", album?.thumbnailUrl)
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