package com.aviv.test

import com.aviv.assessment.listing_details.domain.mapper.ListingDetailsMapper
import com.aviv.assessment.listing_details.data.repository.ListingDetailsRepositoryImpl
import com.aviv.assessment.listings.domain.models.ListingsModel
import com.aviv.core.networking.AppException
import com.aviv.core.networking.apiservice.ListingsApiService
import com.aviv.core.networking.Resource
import com.aviv.core.networking.models.Item
import com.aviv.core.networking.request_handler.ApiRequestHandler
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ListingDetailsRepositoryImplTest {

    private lateinit var apiRequestHandler: ApiRequestHandler
    private lateinit var listingsApiService: ListingsApiService
    private lateinit var listingDetailsMapper: ListingDetailsMapper
    private lateinit var repository: ListingDetailsRepositoryImpl

    @Before
    fun setUp() {
        apiRequestHandler = mockk()
        listingsApiService = mockk()
        listingDetailsMapper = mockk()
        repository = ListingDetailsRepositoryImpl(
            apiRequestHandler,
            listingsApiService,
            listingDetailsMapper
        )
    }

    @Test
    fun `GIVEN getListingDetails returns success THEN repository returns success`() = runBlocking {
        // Given
        val mockListingsModel = mockk<ListingsModel>()
        coEvery {
            apiRequestHandler.handleRequest<Item, ListingsModel>(
                any(),
                any()
            )
        } returns Resource.Success(mockListingsModel)

        // When
        val result = repository.getListingDetails(1)

        // Then
        assertTrue(result is Resource.Success)
        assertEquals(mockListingsModel, (result as Resource.Success).data)
    }

    @Test
    fun `GIVEN getListingDetails returns error THEN repository returns error`() = runBlocking {
        // Given
        val mockException = AppException.DefaultRemoteException("Network error")
        coEvery {
            apiRequestHandler.handleRequest<Item, ListingsModel>(
                any(),
                any()
            )
        } returns Resource.Error(mockException)

        // When
        val result = repository.getListingDetails(1)

        // Then
        assertTrue(result is Resource.Error)
        assertEquals(mockException, (result as Resource.Error).failure)
    }
}
