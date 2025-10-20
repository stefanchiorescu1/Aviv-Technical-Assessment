package com.aviv.core.networking.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ListingsDto(
    @Json(name = "items")
    val items: List<Item?>?,
    @Json(name = "totalCount")
    val totalCount: Int?
)