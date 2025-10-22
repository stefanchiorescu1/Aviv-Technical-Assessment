package com.aviv.assessment.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.aviv.assessment.listing_details.presentation.view.ListingDetailsDestination
import com.aviv.assessment.listings.presentation.view.ListingsDestination
import com.aviv.assessment.navigation.Routes.LISTINGS_ROUTE
import com.aviv.assessment.navigation.Routes.LISTING_DETAIL_ROUTE

@Composable
fun NavGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = LISTINGS_ROUTE
    ) {

        composable(route = LISTINGS_ROUTE) {
            ListingsDestination(
                onNavigateToDetails = { id ->
                    navHostController.navigate(route = "${LISTING_DETAIL_ROUTE}/${id}" )
                }
            )
        }

        composable(
            route = "${LISTING_DETAIL_ROUTE}/{id}",
            arguments = listOf(
                navArgument(name = "id") {
                    type = NavType.IntType
                }
            )
        ) {
            ListingDetailsDestination()
        }
    }
}

const val LISTING_ID = "id"