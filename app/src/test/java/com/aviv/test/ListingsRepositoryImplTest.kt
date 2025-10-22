package com.aviv.test

import com.aviv.assessment.listings.domain.mapper.ListingsMapper
import com.aviv.assessment.listings.domain.models.ListingsModel
import com.aviv.assessment.listings.data.repository.ListingRepositoryImpl
import com.aviv.core.networking.AppException
import com.aviv.core.networking.apiservice.ListingsApiService
import com.aviv.core.networking.Resource
import com.aviv.core.networking.models.ListingsDto
import com.aviv.core.networking.request_handler.ApiRequestHandler
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ListingsRepositoryImplTest {

    private lateinit var apiRequestHandler: ApiRequestHandler
    private lateinit var listingsApiService: ListingsApiService
    private lateinit var listingsMapper: ListingsMapper
    private lateinit var repository: ListingRepositoryImpl

    @Before
    fun setUp() {
        apiRequestHandler = mockk<ApiRequestHandler>()
        listingsApiService = mockk<ListingsApiService>()
        listingsMapper = mockk<ListingsMapper>()
        repository = ListingRepositoryImpl(apiRequestHandler, listingsApiService, listingsMapper)
    }

    @Test
    fun `GIVEN getListings returns success THEN repository returns success`() = runBlocking {
        // Given
        val mockListingsModel = listOf(mockk<ListingsModel>())
        coEvery {
            apiRequestHandler.handleRequest<ListingsDto, List<ListingsModel?>>(
                any(),
                any()
            )
        } returns Resource.Success(mockListingsModel)

        // When
        val result = repository.getListings()

        // Then
        assertTrue(result is Resource.Success)
        assertEquals(mockListingsModel, (result as Resource.Success).data)
    }

    @Test
    fun `GIVEN getListings returns error THEN repository returns error`() = runBlocking {
        // Given
        val mockException = AppException.DefaultRemoteException("Network error")
        coEvery {
            apiRequestHandler.handleRequest<ListingsDto, List<ListingsModel?>>(
                any(),
                any()
            )
        } returns Resource.Error(mockException)

        // When
        val result = repository.getListings()

        // Then
        assertTrue(result is Resource.Error)
        assertEquals(mockException, (result as Resource.Error).failure)
    }
}
