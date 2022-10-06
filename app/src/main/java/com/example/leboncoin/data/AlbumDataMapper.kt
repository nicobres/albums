package com.example.leboncoin.data

import com.example.leboncoin.data.local.AlbumEntity
import com.example.leboncoin.data.remote.AlbumDto


object AlbumDataMapper {

    fun convertDtoToModel(albumDto: AlbumDto) = Album(
        albumDto.id ?: -1,
        albumDto.title ?: "",
        albumDto.thumbnailUrl ?: ""
    )

    fun convertModelToEntity(album: Album) = AlbumEntity(
        album.id,
        album.title,
        album.thumbnailUrl
    )

    fun convertEntityToModel(albumEntity: AlbumEntity) = Album(
        albumEntity.id,
        albumEntity.title,
        albumEntity.thumbnailUrl
    )
}