package com.aviv.assessment.listing_details.data.repository

import com.aviv.assessment.listing_details.domain.mapper.ListingDetailsMapper
import com.aviv.assessment.listing_details.domain.repositories.ListingDetailsRepository
import com.aviv.assessment.listings.domain.models.ListingsModel
import com.aviv.core.networking.Resource
import com.aviv.core.networking.apiservice.ListingsApiService
import com.aviv.core.networking.request_handler.ApiRequestHandler
import javax.inject.Inject

class ListingDetailsRepositoryImpl @Inject constructor(
    private val apiRequestHandler: ApiRequestHandler,
    private val listingsApiService: ListingsApiService,
    private val listingDetailsMapper: ListingDetailsMapper
): ListingDetailsRepository {
    override suspend fun getListingDetails(listingId: Int): Resource<ListingsModel> = when(val response = apiRequestHandler.handleRequest(
        apiCall = {
            listingsApiService.getListingById(listingId)
        },
        successMapper = {
            listingDetailsMapper.mapToDetailsModel(it)
        }
    )) {
        is Resource.Error -> Resource.Error(response.failure)
        is Resource.Success -> Resource.Success(response.data)
    }
}