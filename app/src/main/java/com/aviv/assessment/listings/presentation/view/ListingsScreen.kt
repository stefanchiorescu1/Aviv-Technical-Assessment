package com.aviv.assessment.listings.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aviv.assessment.listings.presentation.viewmodel.ListingsActions
import com.aviv.assessment.listings.presentation.viewmodel.ListingsState
import com.aviv.assessment.listings.presentation.viewmodel.ListingsViewModel
import com.aviv.ui_components.listings_item.ListingsItemComponent
import com.aviv.ui_components.listings_item.ListingsItemModel


@Composable
fun ListingsDestination(
    viewModel: ListingsViewModel = hiltViewModel(),
    onNavigateToDetails: (String) -> Unit
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    ListingsScreen(
        state = state,
        onListingsActions = { action ->
            when(action) {
                is ListingsActions.NavigateToDetails -> onNavigateToDetails(action.id.toString())
            }

        }

    )
}

@Composable
fun ListingsScreen(
    state: ListingsState,
    onListingsActions: (ListingsActions) -> Unit
) {

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier.fillMaxSize()
            .padding(20.dp)
    ) {

        if (state.isLoading) {
            item {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillParentMaxSize()
                ) {
                    CircularProgressIndicator()
                }
            }
        }

        items(state.listings, key = { it!!.id }) { listing ->
            ListingsItemComponent(
                model = ListingsItemModel(
                    name = listing?.professional ?: "",
                    price = listing?.price.toString(),
                    image = listing?.url ?: "",
                    city = listing?.city ?: "",
                ), oncClick = {
                    listing?.let {
                        onListingsActions(ListingsActions.NavigateToDetails(it.id))
                    }
                }
            )
        }

    }

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ListingsScreenPreview() {
    ListingsScreen(state = ListingsState(), onListingsActions = {})

}