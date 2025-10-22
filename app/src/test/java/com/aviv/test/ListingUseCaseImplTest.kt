package com.aviv.test

import com.aviv.assessment.listings.domain.repository.ListingsRepository
import com.aviv.assessment.listings.domain.models.ListingsModel
import com.aviv.assessment.listings.domain.use_cases.ListingUseCaseImpl
import com.aviv.core.networking.AppException
import com.aviv.core.networking.Resource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class ListingUseCaseImplTest {

    private lateinit var listingsRepository: ListingsRepository
    private lateinit var useCase: ListingUseCaseImpl

    @Before
    fun setUp() {
        listingsRepository = mockk()
        useCase = ListingUseCaseImpl(listingsRepository)
    }

    @Test
    fun `GIVEN repository returns success WHEN getListings is called THEN onSuccess is invoked`() =
        runBlocking {
            // Given
            val mockListings = listOf(mockk<ListingsModel>(), null)
            val onSuccess: suspend (List<ListingsModel?>) -> Unit = mockk(relaxed = true)
            val onError: suspend (AppException) -> Unit = mockk(relaxed = true)
            coEvery { listingsRepository.getListings() } returns Resource.Success(mockListings)

            // When
            useCase.getListings(onError, onSuccess)

            // Then
            coVerify { onSuccess(mockListings) }
            coVerify(exactly = 0) { onError(any()) }
        }

    @Test
    fun `GIVEN repository returns error WHEN getListings is called THEN onError is invoked`() =
        runBlocking {
            // Given
            val mockException = AppException.DefaultRemoteException("Network Error")
            val onSuccess: suspend (List<ListingsModel?>) -> Unit = mockk(relaxed = true)
            val onError: suspend (AppException) -> Unit = mockk(relaxed = true)
            coEvery { listingsRepository.getListings() } returns Resource.Error(mockException)

            // When
            useCase.getListings(onError, onSuccess)

            // Then
            coVerify { onError(mockException) }
            coVerify(exactly = 0) { onSuccess(any()) }
        }
}
