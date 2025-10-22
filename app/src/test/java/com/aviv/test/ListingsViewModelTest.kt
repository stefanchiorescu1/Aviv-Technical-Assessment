package com.aviv.test

import app.cash.turbine.test
import com.aviv.assessment.listings.domain.models.ListingsModel
import com.aviv.assessment.listings.domain.use_cases.ListingsUseCase
import com.aviv.assessment.listings.presentation.viewmodel.ListingsActions
import com.aviv.assessment.listings.presentation.viewmodel.ListingsViewModel
import com.aviv.core.networking.AppException
import com.aviv.test.util.MainDispatcherRule
import io.mockk.awaits
import io.mockk.coEvery
import io.mockk.coVerify
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
class ListingsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var listingsUseCase: ListingsUseCase
    private lateinit var viewModel: ListingsViewModel

    @Before
    fun setUp() {
        listingsUseCase = mockk(relaxed = true)
    }

    @Test
    fun `GIVEN use case success WHEN view model is created THEN state emits loading then success`() =
        runTest {
            // Given
            val mockListings = listOf(mockk<ListingsModel>())
            val onSuccessSlot = slot<suspend (List<ListingsModel?>) -> Unit>()

            coEvery { listingsUseCase.getListings(any(), capture(onSuccessSlot)) } coAnswers {
                onSuccessSlot.captured.invoke(mockListings)
            }

            // When
            viewModel = ListingsViewModel(listingsUseCase)

            // Then
            viewModel.state.test {

                val emittedItem = awaitItem()

                // Then, it emits the success state
                assertFalse(emittedItem.isLoading)
                assertEquals(mockListings, emittedItem.listings)
                assertNull(emittedItem.appException)
            }
        }

    @Test
    fun `GIVEN use case error WHEN view model is created THEN state emits loading then error`() =
        runTest {
            // Given
            val mockException = AppException.DefaultRemoteException("Error")
            val onErrorSlot = slot<suspend (AppException) -> Unit>()

            coEvery { listingsUseCase.getListings(capture(onErrorSlot), any()) } coAnswers {
                onErrorSlot.captured.invoke(mockException)
            }

            // When
            viewModel = ListingsViewModel(listingsUseCase)

            // Then
            viewModel.state.test {

                val emittedItem = awaitItem()

                // Then, it emits the error state
                assertFalse(emittedItem.isLoading)
                assertTrue(emittedItem.listings.isEmpty())
                assertEquals(mockException, emittedItem.appException)
            }
        }

    @Test
    fun `WHEN retry action is called THEN state flows from error to loading to success`() =
        runTest {
            // === Initial setup with an error ===
            val mockException = AppException.DefaultRemoteException("Initial Error")
            val onErrorSlot = slot<suspend (AppException) -> Unit>()
            coEvery { listingsUseCase.getListings(capture(onErrorSlot), any()) } coAnswers {
                onErrorSlot.captured.invoke(mockException)
            }

            viewModel = ListingsViewModel(listingsUseCase)

            viewModel.state.test {

                assertEquals(mockException, awaitItem().appException)

                // === Set up for success on retry ===
                val mockListings = listOf(mockk<ListingsModel>())
                val onSuccessSlot = slot<suspend (List<ListingsModel?>) -> Unit>()
                coEvery { listingsUseCase.getListings(any(), capture(onSuccessSlot)) } coAnswers {
                    onSuccessSlot.captured.invoke(mockListings)
                }

                // === When retry action is called ===
                viewModel.onActions(ListingsActions.Retry)

                // === Then assert the new state flow ===
                // State after calling retry: clears exception, sets loading to true
                var retryState = awaitItem()
                assertNull(retryState.appException)

                //fetchListings() update state to loading
                retryState = awaitItem()
                assertTrue(retryState.isLoading)

                // Final success state
                retryState = awaitItem()
                assertEquals(mockListings, retryState.listings)

                // Verify use case was called twice
                coVerify(exactly = 2) { listingsUseCase.getListings(any(), any()) }
            }
        }
}
