package com.aviv.core.networking

sealed class Resource<T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Error<T>(val failure: AppException) : Resource<T>()
}
