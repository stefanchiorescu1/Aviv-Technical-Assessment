package com.aviv.assessment.listing_details.domain.use_cases

import com.aviv.assessment.listings.domain.models.ListingsModel
import com.aviv.core.networking.AppException

interface ListingDetailsUseCase {

    suspend fun getListingDetails(
        listingId: Int,
        onError: suspend (AppException) -> Unit,
        onSuccess: suspend (ListingsModel) -> Unit
    )
}