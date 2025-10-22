package com.aviv.assessment.listings.domain.use_cases

import com.aviv.assessment.listings.domain.repository.ListingsRepository
import com.aviv.assessment.listings.domain.models.ListingsModel
import com.aviv.core.networking.AppException
import com.aviv.core.networking.Resource
import javax.inject.Inject

class ListingUseCaseImpl @Inject constructor(
    private val listingsRepository: ListingsRepository
): ListingsUseCase {
    override suspend fun getListings(
        onError: suspend (AppException) -> Unit,
        onSuccess: suspend (List<ListingsModel?>) -> Unit
    ) {
        when(val response = listingsRepository.getListings()) {
            is Resource.Error -> onError(response.failure)
            is Resource.Success -> onSuccess(response.data)
        }
    }
}