package com.aviv.core.networking.di

import com.aviv.core.networking.request_handler.ApiRequestHandler
import com.aviv.core.networking.request_handler.ApiRequestHandlerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class InternalDiModule {

    @Binds
    @Singleton
    abstract fun bindsApiRequestHandler(requestHandlerImpl: ApiRequestHandlerImpl): ApiRequestHandler
}