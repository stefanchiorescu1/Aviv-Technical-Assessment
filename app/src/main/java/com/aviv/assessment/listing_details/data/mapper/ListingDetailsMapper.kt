package com.aviv.assessment.listing_details.data.mapper

import com.aviv.assessment.listings.domain.models.ListingsModel
import com.aviv.core.networking.models.Item

interface ListingDetailsMapper {

    fun mapToDetailsModel(listing: Item): ListingsModel
}