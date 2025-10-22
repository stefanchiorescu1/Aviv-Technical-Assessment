package com.aviv.assessment.listing_details.presentation.viewmodel

import com.aviv.assessment.listings.domain.models.ListingsModel
import com.aviv.core.networking.AppException

data class ListingDetailsState(
    val isLoading: Boolean = false,
    val listingDetails: ListingsModel = ListingsModel(
        id = 0,
        bedrooms = null,
        city = "",
        area = 0.0,
        url = "",
        price = 0.0,
        professional = "",
        propertyType = "",
        rooms = null
    ),
    val appException: AppException? = null
)
