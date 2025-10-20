package com.aviv.core.networking.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Item(
    @Json(name = "area")
    val area: Int?,
    @Json(name = "bedrooms")
    val bedrooms: Int?,
    @Json(name = "city")
    val city: String?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "offerType")
    val offerType: Int?,
    @Json(name = "price")
    val price: Int?,
    @Json(name = "professional")
    val professional: String?,
    @Json(name = "propertyType")
    val propertyType: String?,
    @Json(name = "rooms")
    val rooms: Int?,
    @Json(name = "url")
    val url: String?
)