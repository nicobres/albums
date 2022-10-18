package com.example.leboncoin.data.remote

import com.squareup.moshi.Json

data class AlbumDto (
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "thumbnailUrl") val thumbnailUrl: String
)