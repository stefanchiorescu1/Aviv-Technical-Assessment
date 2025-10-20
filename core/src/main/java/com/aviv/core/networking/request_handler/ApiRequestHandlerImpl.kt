package com.aviv.core.networking.request_handler

import com.aviv.core.networking.AppException
import com.aviv.core.networking.Resource
import com.squareup.moshi.JsonDataException
import java.io.InterruptedIOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class ApiRequestHandlerImpl @Inject constructor() : ApiRequestHandler {

    override suspend fun <T : Any, R> handleRequest(
        apiCall: suspend () -> T,
        successMapper: (T) -> R
    ): Resource<R> {
        try {
            val response = apiCall.invoke()
            return Resource.Success(data = successMapper(response))
        } catch (jsonDataException: JsonDataException) {
            return Resource.Error(failure = AppException.UnknownException)
        } catch (timeoutException: SocketTimeoutException) {
            return Resource.Error(failure = AppException.NoServiceException)
        } catch (timeoutException: InterruptedIOException) {
            return Resource.Error(failure = AppException.NoServiceException)
        } catch (unknownHostException: UnknownHostException) {
            return Resource.Error(failure = AppException.NoServiceException)
        } catch (exception: Exception) {
            return Resource.Error(failure = AppException.UnknownException)
        }
    }
}
