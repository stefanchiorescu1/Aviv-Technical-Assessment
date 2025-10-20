package com.aviv.core.networking

import com.aviv.core.networking.models.Item
import com.aviv.core.networking.models.ListingsDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ListingsApiService {

    @GET("")
    suspend fun getListings(): ListingsDto

    @GET("listings/{id}.json")
    suspend fun getListingById(@Path("id") id: Int): Item


}