package com.aviv.assessment.listing_details.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aviv.assessment.listing_details.domain.use_cases.ListingDetailsUseCase
import com.aviv.assessment.navigation.LISTING_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ListingDetailsViewModel @Inject constructor(
    private val listingDetailsUseCase: ListingDetailsUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {


    private val _state = MutableStateFlow(ListingDetailsState())
    val state = _state.asStateFlow()


    init {
        savedStateHandle.get<Int>(LISTING_ID)?.let {
            getListingDetails(it)
        }

    }

    private fun getListingDetails(listingId: Int) {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch(Dispatchers.IO) {
            listingDetailsUseCase.getListingDetails(
                listingId = listingId,
                onError = {
                    withContext(Dispatchers.Main) {
                        _state.update {
                            it.copy(isLoading = false, error = it.error)
                        }
                    }
                },
                onSuccess = { model ->
                    withContext(Dispatchers.Main) {
                        _state.update {
                            it.copy(isLoading = false, listingDetails = model)
                        }
                    }
                },
            )
        }
    }



}