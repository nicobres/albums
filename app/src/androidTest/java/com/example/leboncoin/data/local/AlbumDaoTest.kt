package com.example.leboncoin.data.local

import org.junit.Assert.*


import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class AlbumDaoTest {

    private lateinit var db: AppDatabase
    private lateinit var albumDao: AlbumDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java).build()
        albumDao = db.albumDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAllAndGetAllTest() {
        // Act
        albumDao.insertAll(
            AlbumEntity(1, "title", "thumbnail")
        )

        val albumEntities = albumDao.getAll()

        // Assert
        assertNotNull(albumEntities)
        assertEquals(1, albumEntities!!.size)

        val albumEntity = albumEntities.first()
        assertEquals(1, albumEntity.id)
        assertEquals("title", albumEntity.title)
        assertEquals("thumbnail", albumEntity.thumbnailUrl)
    }

    @Test
    @Throws(Exception::class)
    fun deleteAllTest() {
        // Arrange
        albumDao.insertAll(
            AlbumEntity(1, "title", "thumbnail")
        )

        val albumEntities = albumDao.getAll()

        assertNotNull(albumEntities)
        assertEquals(1, albumEntities!!.size)

        // Act
        albumDao.deleteAll()

        // Assert
        assert(albumDao.getAll()!!.isEmpty())
    }
}