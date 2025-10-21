package com.aviv.assessment.listings.domain.models

data class ListingsModel(
    val id: Int,
    val bedrooms: Int?,
    val city: String,
    val area: Double,
    val url: String?,
    val price: Double,
    val professional: String,
    val propertyType: String,
    val rooms: Int?,
)
