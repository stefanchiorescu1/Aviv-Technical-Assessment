package com.aviv.assessment.listing_details.di

import com.aviv.assessment.listing_details.data.mapper.ListingDetailsMapper
import com.aviv.assessment.listing_details.data.mapper.ListingDetailsMapperImpl
import com.aviv.assessment.listing_details.data.repository.ListingDetailsRepository
import com.aviv.assessment.listing_details.domain.repositories.ListingDetailsRepositoryImpl
import com.aviv.assessment.listing_details.domain.use_cases.ListingDetailsUseCase
import com.aviv.assessment.listing_details.domain.use_cases.ListingDetailsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ListingDetailsModule {

    @Binds
    @Singleton
    abstract fun bindsListingDetailsMapper(listingDetailsMapperImpl: ListingDetailsMapperImpl): ListingDetailsMapper

    @Binds
    @Singleton
    abstract fun bindsListingDetailsRepository(listingDetailsRepositoryImpl: ListingDetailsRepositoryImpl): ListingDetailsRepository

    @Binds
    @Singleton
    abstract fun bindsListingDetailsUseCase(listingDetailsUseCaseImpl: ListingDetailsUseCaseImpl): ListingDetailsUseCase
}