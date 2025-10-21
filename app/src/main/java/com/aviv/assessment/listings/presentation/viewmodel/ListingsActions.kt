package com.aviv.assessment.listings.presentation.viewmodel

sealed interface ListingsActions {

    data class NavigateToDetails(val id: Int): ListingsActions
}