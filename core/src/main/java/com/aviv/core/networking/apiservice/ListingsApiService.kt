package com.aviv.core.networking.apiservice

import com.aviv.core.networking.models.Item
import com.aviv.core.networking.models.ListingsDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ListingsApiService {

    @GET("listings.json")
    suspend fun getListings(): ListingsDto

    @GET("listings/{id}.json")
    suspend fun getListingById(@Path("id") id: Int): Item


}