package com.example.utils

sealed class RequestResult<out T> {
    data class Success<out T>(
        val result: T,
    ) : RequestResult<T>()

    data class Error(
        val errorResponse: String,
    ) : RequestResult<Nothing>() {

        override fun toString(): String {
            return "Error(errorResponse='$errorResponse')"
        }
    }
}