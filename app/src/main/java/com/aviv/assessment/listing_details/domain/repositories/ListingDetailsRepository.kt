package com.aviv.assessment.listing_details.domain.repositories

import com.aviv.assessment.listings.domain.models.ListingsModel
import com.aviv.core.networking.Resource

interface ListingDetailsRepository {

    suspend fun getListingDetails(listingId: Int): Resource<ListingsModel>
}