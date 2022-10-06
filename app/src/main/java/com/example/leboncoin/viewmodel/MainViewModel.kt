package com.example.leboncoin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leboncoin.data.Album
import com.example.leboncoin.usecase.FetchAlbumUseCase
import com.example.leboncoin.utils.RequestResult
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent


class MainViewModel(
    private val fetchAlbumUseCase : FetchAlbumUseCase,
) : ViewModel(), KoinComponent {

    private val _albumsLiveData = MutableLiveData<RequestResult<List<Album>>>()
    val albumsLiveData: LiveData<RequestResult<List<Album>>> = _albumsLiveData

    init {
        fetchAlbums()
    }

    fun fetchAlbums() {
        viewModelScope.launch {
            _albumsLiveData.value = fetchAlbumUseCase.execute()
        }
    }
}