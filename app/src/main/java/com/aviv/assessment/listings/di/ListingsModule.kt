package com.aviv.assessment.listings.di

import com.aviv.assessment.listings.data.mapper.ListingMapperImpl
import com.aviv.assessment.listings.data.mapper.ListingsMapper
import com.aviv.assessment.listings.data.repository.ListingsRepository
import com.aviv.assessment.listings.domain.repository.ListingRepositoryImpl
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


}