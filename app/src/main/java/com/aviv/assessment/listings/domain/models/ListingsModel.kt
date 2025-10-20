package com.aviv.assessment.listings.domain.models

data class ListingsModel(
    val id: Int,
    val bedrooms: Int?,
    val city: String,
    val area: Int,
    val url: String?,
    val price: Int,
    val professional: String,
    val propertyType: String,
    val rooms: Int?,
)
