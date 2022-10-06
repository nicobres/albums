package com.example.leboncoin.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = ALBUM_TABLE)
data class AlbumEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "thumbnailUrl") val thumbnailUrl: String
)