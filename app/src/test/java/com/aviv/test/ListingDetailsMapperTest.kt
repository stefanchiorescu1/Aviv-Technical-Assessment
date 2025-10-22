package com.aviv.test

import com.aviv.assessment.listing_details.data.mapper.ListingDetailsMapperImpl
import com.aviv.core.networking.models.Item
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class ListingDetailsMapperTest {

    private val mapper = ListingDetailsMapperImpl()

    @Test
    fun `GIVEN a valid item WHEN mapping THEN return a listings model`() {
        // Given
        val item = mockk<Item> {
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

        // When
        val result = mapper.mapToDetailsModel(item)

        // Then
        assertEquals(1, result.id)
        assertEquals(2, result.bedrooms)
        assertEquals("Paris", result.city)
        assertEquals(100.0, result.area, 0.0)
        assertEquals("url", result.url)
        assertEquals(100000.0, result.price, 0.0)
        assertEquals("professional", result.professional)
        assertEquals("apartment", result.propertyType)
        assertEquals(3, result.rooms)
    }

    @Test
    fun `GIVEN an item with null nullable fields WHEN mapping THEN return a listings model with nulls`() {
        // Given
        val item = mockk<Item> {
            every { id } returns 1
            every { bedrooms } returns null
            every { city } returns "Paris"
            every { area } returns 100.0
            every { url } returns null
            every { price } returns 100000.0
            every { professional } returns "professional"
            every { propertyType } returns "apartment"
            every { rooms } returns null
        }

        // When
        val result = mapper.mapToDetailsModel(item)

        // Then
        assertEquals(1, result.id)
        assertNull(result.bedrooms)
        assertNull(result.url)
        assertNull(result.rooms)
    }

    @Test
    fun `GIVEN item with null non-nullable fields WHEN mapping THEN return model with default values`() {
        // Given
        val item = mockk<Item>(relaxed = true) { // relaxed to allow returning nulls by default
            every { id } returns null
            every { city } returns null
            every { area } returns null
            every { price } returns null
            every { professional } returns null
            every { propertyType } returns null
        }

        // When
        val result = mapper.mapToDetailsModel(item)

        // Then
        assertEquals(0, result.id)
        assertEquals("", result.city)
        assertEquals(0.0, result.area, 0.0)
        assertEquals(0.0, result.price, 0.0)
        assertEquals("", result.professional)
        assertEquals("", result.propertyType)
    }
}
