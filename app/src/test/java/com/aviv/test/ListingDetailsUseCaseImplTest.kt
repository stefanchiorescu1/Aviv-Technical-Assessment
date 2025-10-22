package com.aviv.test

import com.aviv.assessment.listing_details.domain.repositories.ListingDetailsRepository
import com.aviv.assessment.listing_details.domain.use_cases.ListingDetailsUseCaseImpl
import com.aviv.assessment.listings.domain.models.ListingsModel
import com.aviv.core.networking.AppException
import com.aviv.core.networking.Resource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class ListingDetailsUseCaseImplTest {

    private lateinit var listingDetailsRepository: ListingDetailsRepository
    private lateinit var useCase: ListingDetailsUseCaseImpl

    @Before
    fun setUp() {
        listingDetailsRepository = mockk()
        useCase = ListingDetailsUseCaseImpl(listingDetailsRepository)
    }

    @Test
    fun `GIVEN repo returns success WHEN get details is called THEN onSuccess is invoked`() =
        runBlocking {
            // Given
            val mockListing = mockk<ListingsModel>()
            val onSuccess: suspend (ListingsModel) -> Unit = mockk(relaxed = true)
            val onError: suspend (AppException) -> Unit = mockk(relaxed = true)
            val listingId = 1
            coEvery { listingDetailsRepository.getListingDetails(listingId) } returns Resource.Success(
                mockListing
            )

            // When
            useCase.getListingDetails(listingId, onError, onSuccess)

            // Then
            coVerify { onSuccess(mockListing) }
            coVerify(exactly = 0) { onError(any()) }
        }

    @Test
    fun `GIVEN repo returns error WHEN get details is called THEN onError is invoked`() =
        runBlocking {
            // Given
            val mockException = AppException.DefaultRemoteException("Network Error")
            val onSuccess: suspend (ListingsModel) -> Unit = mockk(relaxed = true)
            val onError: suspend (AppException) -> Unit = mockk(relaxed = true)
            val listingId = 1
            coEvery { listingDetailsRepository.getListingDetails(listingId) } returns Resource.Error(
                mockException
            )

            // When
            useCase.getListingDetails(listingId, onError, onSuccess)

            // Then
            coVerify { onError(mockException) }
            coVerify(exactly = 0) { onSuccess(any()) }
        }
}
