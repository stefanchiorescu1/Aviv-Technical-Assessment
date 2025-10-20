package com.aviv.assessment.listings.presentation.viewmodel

import com.aviv.assessment.listings.domain.models.ListingsModel
import com.aviv.core.networking.AppException

data class ListingsState(
    val isLoading: Boolean = false,
    val listings: List<ListingsModel?> = emptyList(),
    val appException: AppException? = null
)
