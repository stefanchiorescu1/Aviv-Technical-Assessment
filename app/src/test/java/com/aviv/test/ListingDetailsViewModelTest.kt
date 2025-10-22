package com.aviv.test

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.aviv.assessment.listing_details.domain.use_cases.ListingDetailsUseCase
import com.aviv.assessment.listing_details.presentation.viewmodel.ListingDetailsActions
import com.aviv.assessment.listing_details.presentation.viewmodel.ListingDetailsViewModel
import com.aviv.assessment.listings.domain.models.ListingsModel
import com.aviv.assessment.navigation.LISTING_ID
import com.aviv.core.networking.AppException
import com.aviv.test.util.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ListingDetailsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var listingDetailsUseCase: ListingDetailsUseCase
    private lateinit var savedStateHandle: SavedStateHandle
    private lateinit var viewModel: ListingDetailsViewModel

    private val listingId = 123

    @Before
    fun setUp() {
        listingDetailsUseCase = mockk(relaxed = true)
        savedStateHandle = mockk(relaxed = true)
        every { savedStateHandle.get<Int>(LISTING_ID) } returns listingId
    }

    @Test
    fun `GIVEN use case success WHEN view model is created THEN state emits loading then success`() =
        runTest {
            // Given
            val mockListing = mockk<ListingsModel>()
            val onSuccessSlot = slot<suspend (ListingsModel) -> Unit>()
            coEvery {
                listingDetailsUseCase.getListingDetails(
                    any(),
                    any(),
                    capture(onSuccessSlot)
                )
            } coAnswers {
                onSuccessSlot.captured.invoke(mockListing)
            }

            // When
            viewModel = ListingDetailsViewModel(listingDetailsUseCase, savedStateHandle)

            // Then
            viewModel.state.test {
                val emittedItem = awaitItem()

                assertFalse(emittedItem.isLoading)
                assertEquals(mockListing, emittedItem.listingDetails)
                assertNull(emittedItem.appException)
            }
        }

    @Test
    fun `GIVEN use case error WHEN view model is created THEN state emits loading then error`() =
        runTest {
            // Given
            val mockException = AppException.DefaultRemoteException("Error")
            val onErrorSlot = slot<suspend (AppException) -> Unit>()
            coEvery {
                listingDetailsUseCase.getListingDetails(
                    any(),
                    capture(onErrorSlot),
                    any()
                )
            } coAnswers {
                onErrorSlot.captured.invoke(mockException)
            }

            // When
            viewModel = ListingDetailsViewModel(listingDetailsUseCase, savedStateHandle)

            // Then
            viewModel.state.test {
                val emittedItem = awaitItem()

                assertFalse(emittedItem.isLoading)
                assertEquals(mockException, emittedItem.appException)
            }
        }

    @Test
    fun `WHEN retry is called THEN state flows from error to loading to success`() = runTest {
        // === Initial setup with an error ===
        val mockException = AppException.DefaultRemoteException("Error")
        val onErrorSlot = slot<suspend (AppException) -> Unit>()
        coEvery {
            listingDetailsUseCase.getListingDetails(
                any(),
                capture(onErrorSlot),
                any()
            )
        } coAnswers {
            onErrorSlot.captured.invoke(mockException)
        }
        viewModel = ListingDetailsViewModel(listingDetailsUseCase, savedStateHandle)

        viewModel.state.test {
            // Consume initial loading and error states
            val firstEmission = awaitItem()
            assertEquals(mockException, firstEmission.appException)

            // === Set up for success on retry ===
            val mockListing = mockk<ListingsModel>()
            val onSuccessSlot = slot<suspend (ListingsModel) -> Unit>()
            coEvery {
                listingDetailsUseCase.getListingDetails(
                    any(),
                    any(),
                    capture(onSuccessSlot)
                )
            } coAnswers {
                onSuccessSlot.captured.invoke(mockListing)
            }

            // === When retry action is called ===
            viewModel.onActions(ListingDetailsActions.Retry(listingId))

            // === Then assert the new state flow ===
            var retryState = awaitItem()
            assertNull(retryState.appException)

            //getListingDetails(listingsID: Int) triggers isLoading true
            retryState = awaitItem()
            assertTrue(retryState.isLoading)

            retryState = awaitItem()
            assertFalse(retryState.isLoading)
            assertEquals(mockListing, retryState.listingDetails)

            // Verify use case was called twice
            coVerify(exactly = 2) {
                listingDetailsUseCase.getListingDetails(
                    eq(listingId),
                    any(),
                    any()
                )
            }
        }
    }
}
