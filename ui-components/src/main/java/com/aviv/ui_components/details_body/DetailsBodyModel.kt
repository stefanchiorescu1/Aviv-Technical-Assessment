package com.aviv.ui_components.details_body

data class DetailsBodyModel(
    val name: String,
    val price: Double,
    val bedrooms: Int?,
    val rooms: Int?,
    val city: String,
    val propertyType: String,
)
