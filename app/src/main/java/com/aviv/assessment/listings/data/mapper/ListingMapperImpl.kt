package com.aviv.assessment.listings.data.mapper

import com.aviv.assessment.listings.domain.models.ListingsModel
import com.aviv.core.networking.models.ListingsDto
import javax.inject.Inject

class ListingMapperImpl @Inject constructor(): ListingsMapper {

    override fun mapToListingsModel(listingsResponse: ListingsDto): List<ListingsModel?> = listingsResponse.items?.let { response ->
        response.map { item ->
            item?.let { listingItem ->
                ListingsModel(
                    id = listingItem.id ?: Int.MAX_VALUE,
                    bedrooms = listingItem.bedrooms,
                    city = listingItem.city ?: "",
                    area = listingItem.area ?: 0.0,
                    url = listingItem.url,
                    price = listingItem.price ?: 0.0,
                    professional = listingItem.professional ?: "",
                    propertyType = listingItem.propertyType ?: "",
                    rooms = listingItem.rooms
                )
            }
        }
    } ?: emptyList()
}