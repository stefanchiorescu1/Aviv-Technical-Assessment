package com.aviv.assessment.listing_details.presentation.view

import android.widget.Space
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aviv.assessment.listing_details.presentation.viewmodel.ListingDetailsState
import com.aviv.assessment.listing_details.presentation.viewmodel.ListingDetailsViewModel
import com.aviv.ui_components.details_body.DetailsBodyComponent
import com.aviv.ui_components.details_body.DetailsBodyModel
import com.aviv.ui_components.details_header.DetailsHeaderComponent
import com.aviv.ui_components.details_header.DetailsHeaderModel
import java.nio.file.WatchEvent

@Composable
fun ListingDetailsDestination(
    viewModel: ListingDetailsViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    ListingDetailsScreen(state = state)

}

@Composable
fun ListingDetailsScreen(
    state: ListingDetailsState
) {

    if (state.isLoading) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator()
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        DetailsHeaderComponent(model = DetailsHeaderModel(
            url = state.listingDetails.url ?: ""
        ))

        Spacer(modifier = Modifier.height(20.dp))

        DetailsBodyComponent(model = DetailsBodyModel(
            name = state.listingDetails.propertyType,
            price = state.listingDetails.price,
            bedrooms = state.listingDetails.bedrooms,
            rooms = state.listingDetails.rooms,
            city = state.listingDetails.city,
            propertyType = state.listingDetails.propertyType
        ))

    }


}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ListingDetailsScreenPreview() {
    ListingDetailsScreen(state = ListingDetailsState())
}