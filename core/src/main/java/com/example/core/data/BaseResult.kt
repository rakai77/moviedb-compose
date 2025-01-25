package com.example.core.data

sealed class BaseResult<out T> {
    data class Success<T> (val data: T) : BaseResult<T>()
    data class Error(val errorMessage: String, val statusCode: Int? = null) : BaseResult<Nothing>()
}