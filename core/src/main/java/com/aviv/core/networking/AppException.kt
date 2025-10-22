package com.aviv.core.networking

sealed class AppException() : Exception() {

    data object NoServiceException : AppException()

    data object TimeoutException : AppException()

    data object UnknownException : AppException()

    data class DefaultRemoteException(val transactionMessage: String) : AppException()


}
