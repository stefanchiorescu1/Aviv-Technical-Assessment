package com.aviv.assessment.listings.data.mapper

import com.aviv.assessment.listings.domain.models.ListingsModel
import com.aviv.core.networking.models.ListingsDto
import javax.inject.Inject

class ListingMapperImpl @Inject constructor(): ListingsMapper {

    override fun mapToListingsModel(listingsResponse: ListingsDto): List<ListingsModel?> = listingsResponse.items?.let { response ->
        response.map { item ->
            item?.let { listingItem ->
                ListingsModel(
                    id = listingItem.id,
                    bedrooms = listingItem.bedrooms,
                    city = listingItem.city,
                    area = listingItem.area,
                    url = listingItem.url,
                    price = listingItem.price,
                    professional = listingItem.professional,
                    propertyType = listingItem.propertyType,
                    rooms = listingItem.rooms
                )
            }
        }
    } ?: emptyList()
}