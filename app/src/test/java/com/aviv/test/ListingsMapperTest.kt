package com.aviv.test

import com.aviv.assessment.listings.data.mapper.ListingMapperImpl
import com.aviv.core.networking.models.Item
import com.aviv.core.networking.models.ListingsDto
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class ListingsMapperTest {

    private val mapper = ListingMapperImpl()

    @Test
    fun `GIVEN a valid listings dto WHEN mapping THEN return a listings model`() {
        // Given
        val listingsDto = mockk<ListingsDto> {
            every { items } returns listOf(
                mockk<Item> {
                    every { id } returns 1
                    every { bedrooms } returns 2
                    every { city } returns "Paris"
                    every { area } returns 100.0
                    every { url } returns "url"
                    every { price } returns 100000.0
                    every { professional } returns "professional"
                    every { propertyType } returns "apartment"
                    every { rooms } returns 3
                }
            )
        }

        // When
        val result = mapper.mapToListingsModel(listingsDto)

        // Then
        assertEquals(1, result.size)
        assertEquals(1, result[0]?.id)
        assertEquals(2, result[0]?.bedrooms)
        assertEquals("Paris", result[0]?.city)
        assertEquals(100.0, result[0]!!.area, 0.0)
        assertEquals("url", result[0]?.url)
        assertEquals(100000.0, result[0]!!.price, 0.0)
        assertEquals("professional", result[0]?.professional)
        assertEquals("apartment", result[0]?.propertyType)
        assertEquals(3, result[0]?.rooms)
    }

    @Test
    fun `GIVEN a listings dto with a null item WHEN mapping THEN return a list with a null item`() {
        // Given
        val listingsDto = mockk<ListingsDto> {
            every { items } returns listOf(null)
        }

        // When
        val result = mapper.mapToListingsModel(listingsDto)

        // Then
        assertEquals(1, result.size)
        assertEquals(null, result[0])
    }

    @Test
    fun `GIVEN a listings dto with a null items list WHEN mapping THEN return an empty list`() {
        // Given
        val listingsDto = mockk<ListingsDto> {
            every { items } returns null
        }

        // When
        val result = mapper.mapToListingsModel(listingsDto)

        // Then
        assertEquals(0, result.size)
    }
}
