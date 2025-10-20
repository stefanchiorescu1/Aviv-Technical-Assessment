package com.aviv.assessment.listings.presentation.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@Composable
fun ListingsDestination(
    viewModel: ListingsViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    ListingsScreen(
        state = state
    )
}

@Composable
fun ListingsScreen(
    state: ListingsState
) {

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ListingsScreenPreview() {
    ListingsScreen(state = ListingsState())
}