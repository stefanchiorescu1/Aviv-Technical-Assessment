package com.aviv.assessment.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aviv.assessment.navigation.Routes.LISTINGS_ROUTE

@Composable
fun NavGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = LISTINGS_ROUTE
    ) {

        composable(route = LISTINGS_ROUTE) {

        }
    }
}