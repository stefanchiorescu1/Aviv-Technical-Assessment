package com.aviv.assessment.listings.domain.use_cases

import com.aviv.assessment.listings.domain.models.ListingsModel
import com.aviv.core.networking.AppException

interface ListingsUseCase {

    suspend fun getListings(
        onError: (AppException) -> Unit,
        onSuccess: (List<ListingsModel?>) -> Unit
    )
}