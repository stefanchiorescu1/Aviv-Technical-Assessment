package com.aviv.assessment.listing_details.domain.use_cases

import com.aviv.assessment.listing_details.domain.repositories.ListingDetailsRepository
import com.aviv.assessment.listings.domain.models.ListingsModel
import com.aviv.core.networking.AppException
import com.aviv.core.networking.Resource
import javax.inject.Inject

class ListingDetailsUseCaseImpl @Inject constructor(
    private val listingDetailsRepository: ListingDetailsRepository
): ListingDetailsUseCase {
    override suspend fun getListingDetails(
        listingId: Int,
        onError: suspend (AppException) -> Unit,
        onSuccess: suspend (ListingsModel) -> Unit
    ) {
        when(val response = listingDetailsRepository.getListingDetails(listingId)) {
            is Resource.Error -> onError(response.failure)
            is Resource.Success -> onSuccess(response.data)
        }
    }
}