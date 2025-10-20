package com.aviv.core.networking.request_handler

import com.aviv.core.networking.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ApiRequestHandler {

    suspend fun<T : Any, R> handleRequest(
        apiCall: suspend () -> T,
        successMapper: (T) -> R
    ): Resource<R>
}
