package com.aviv.assessment.listings.domain.repository

import com.aviv.assessment.listings.data.mapper.ListingsMapper
import com.aviv.assessment.listings.data.repository.ListingsRepository
import com.aviv.assessment.listings.domain.models.ListingsModel
import com.aviv.core.networking.ListingsApiService
import com.aviv.core.networking.Resource
import com.aviv.core.networking.request_handler.ApiRequestHandler
import javax.inject.Inject

class ListingRepositoryImpl @Inject constructor(
    private val apiRequestHandler: ApiRequestHandler,
    private val listingsApiService: ListingsApiService,
    private val listingsMapper: ListingsMapper
): ListingsRepository {
    override suspend fun getListings(): Resource<List<ListingsModel?>> = when(val response = apiRequestHandler.handleRequest(
        apiCall = {
            listingsApiService.getListings()
        },
        successMapper = {
            listingsMapper.mapToListingsModel(it)
        }
    )) {
        is Resource.Error -> Resource.Error(response.failure)
        is Resource.Success -> Resource.Success(response.data)
    }
}