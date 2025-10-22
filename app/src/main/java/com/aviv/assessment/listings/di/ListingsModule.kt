package com.aviv.assessment.listings.di

import com.aviv.assessment.listings.data.mapper.ListingMapperImpl
import com.aviv.assessment.listings.domain.mapper.ListingsMapper
import com.aviv.assessment.listings.domain.repository.ListingsRepository
import com.aviv.assessment.listings.data.repository.ListingRepositoryImpl
import com.aviv.assessment.listings.domain.use_cases.ListingUseCaseImpl
import com.aviv.assessment.listings.domain.use_cases.ListingsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ListingsModule {

    @Binds
    @Singleton
    abstract fun bindsListingsMapper(listingMapperImpl: ListingMapperImpl): ListingsMapper

    @Binds
    @Singleton
    abstract fun bindsListingsRepository(listingRepositoryImpl: ListingRepositoryImpl): ListingsRepository

    @Binds
    @Singleton
    abstract fun bindsListingsUseCase(listingUseCaseImpl: ListingUseCaseImpl): ListingsUseCase


}