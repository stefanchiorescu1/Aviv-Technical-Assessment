package com.aviv.assessment.listings.domain.mapper

import com.aviv.assessment.listings.domain.models.ListingsModel
import com.aviv.core.networking.models.ListingsDto

interface ListingsMapper {

    fun mapToListingsModel(listingsResponse: ListingsDto): List<ListingsModel?>
}