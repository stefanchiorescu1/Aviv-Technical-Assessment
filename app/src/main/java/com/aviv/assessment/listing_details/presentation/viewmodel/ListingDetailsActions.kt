package com.aviv.assessment.listing_details.presentation.viewmodel

sealed interface ListingDetailsActions {

    data class Retry(val id: Int): ListingDetailsActions
}