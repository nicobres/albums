package com.example.leboncoin.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

const val ALBUM_TABLE = "album_table"

@Dao
interface AlbumDao {
    @Query("SELECT * FROM ALBUM_TABLE")
    fun getAll(): List<AlbumEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg albums: AlbumEntity)

    @Query("DELETE FROM ALBUM_TABLE")
    fun deleteAll()
}