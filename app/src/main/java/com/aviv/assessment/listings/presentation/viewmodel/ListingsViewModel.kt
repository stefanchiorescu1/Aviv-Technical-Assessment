package com.aviv.assessment.listings.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aviv.assessment.listings.domain.use_cases.ListingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ListingsViewModel @Inject constructor(
    private val listingsUseCase: ListingsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ListingsState())
    val state = _state.asStateFlow()


    init {
        fetchListings()
    }

    private fun fetchListings() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch(Dispatchers.IO) {
            listingsUseCase.getListings(
                onError = { appException ->
                    withContext(Dispatchers.Main) {
                        _state.update {
                            it.copy(isLoading = false, appException = appException)
                        }
                    }
                },
                onSuccess = { listings ->
                    withContext(Dispatchers.Main) {
                        _state.update {
                            it.copy(isLoading = false, listings = listings)
                        }
                    }
                }
            )
        }
    }


}