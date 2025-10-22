package com.aviv.assessment.listing_details.data.mapper

import com.aviv.assessment.listing_details.domain.mapper.ListingDetailsMapper
import com.aviv.assessment.listings.domain.models.ListingsModel
import com.aviv.core.networking.models.Item
import javax.inject.Inject

class ListingDetailsMapperImpl @Inject constructor(): ListingDetailsMapper {

    override fun mapToDetailsModel(listing: Item): ListingsModel = ListingsModel(
        id = listing.id ?: 0,
        bedrooms = listing.bedrooms,
        city = listing.city ?: "",
        area = listing.area ?: 0.0,
        url = listing.url,
        price = listing.price ?: 0.0,
        professional = listing.professional ?: "",
        propertyType = listing.propertyType ?: "",
        rooms = listing.rooms,
    )
}