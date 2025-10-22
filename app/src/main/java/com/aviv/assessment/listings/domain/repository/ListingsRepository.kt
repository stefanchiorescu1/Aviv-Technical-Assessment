package com.aviv.assessment.listings.domain.repository

import com.aviv.assessment.listings.domain.models.ListingsModel
import com.aviv.core.networking.Resource

interface ListingsRepository {

    suspend fun getListings(): Resource<List<ListingsModel?>>

}