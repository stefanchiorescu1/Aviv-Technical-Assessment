package com.aviv.assessment.listing_details.data.mapper

import com.aviv.assessment.listings.domain.models.ListingsModel
import com.aviv.core.networking.models.Item
import javax.inject.Inject

class ListingDetailsMapperImpl @Inject constructor(): ListingDetailsMapper {

    override fun mapToDetailsModel(listing: Item): ListingsModel = ListingsModel(
        id = listing.id,
        bedrooms = listing.bedrooms,
        city = listing.city,
        area = listing.area,
        url = listing.url,
        price = listing.price,
        professional = listing.professional,
        propertyType = listing.propertyType,
        rooms = listing.rooms,
    )
}