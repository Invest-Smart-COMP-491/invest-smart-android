package com.comp491.investsmart.navigation

import androidx.compose.runtime.Composable
import com.comp491.investsmart.ui.home.HomeScreen
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.comp491.investsmart.ui.favourites.FavouritesScreen
import com.comp491.investsmart.ui.profile.ProfileScreen
import com.comp491.investsmart.ui.search.SearchScreen

@Composable
fun NavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = NavRoute.Home.route
    ) {
        addHomeScreen(navController, this)
    }
}

private fun addHomeScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(route = NavRoute.Home.route) {
        HomeScreen()
    }

    navGraphBuilder.composable(route = NavRoute.Search.route) {
        SearchScreen()
    }

    navGraphBuilder.composable(route = NavRoute.Favourites.route) {
        FavouritesScreen()
    }

    navGraphBuilder.composable(route = NavRoute.Profile.route) {
        ProfileScreen()
    }
}
