package com.comp491.investsmart.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.comp491.investsmart.ui.home.HomeScreen
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.comp491.investsmart.ui.assetdetail.AssetDetailScreen
import com.comp491.investsmart.ui.comments.CommentsScreen
import com.comp491.investsmart.ui.favourites.FavouritesScreen
import com.comp491.investsmart.ui.login.LoginScreen
import com.comp491.investsmart.ui.profile.ProfileScreen
import com.comp491.investsmart.ui.search.SearchScreen
import com.comp491.investsmart.ui.settings.SettingsScreen

@Composable
fun NavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = NavRoute.Login.route
    ) {
        addScreens(navController, this)
    }
}

private fun addScreens(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(route = NavRoute.Home.route) {
        HomeScreen(hiltViewModel(), navController)
    }

    navGraphBuilder.composable(route = NavRoute.Search.route) {
        SearchScreen(hiltViewModel(), navController)
    }

    navGraphBuilder.composable(route = NavRoute.Favourites.route) {
        FavouritesScreen(hiltViewModel(), navController)
    }

    navGraphBuilder.composable(route = NavRoute.Profile.route) {
        ProfileScreen(hiltViewModel(), navController)
    }

    navGraphBuilder.composable(route = NavRoute.Settings.route) {
        SettingsScreen(hiltViewModel(), navController)
    }

    navGraphBuilder.composable(route = NavRoute.Login.route) {
        LoginScreen(hiltViewModel(), navController)
    }

    navGraphBuilder.composable(
        route = NavRoute.Comments.withArgsFormat(NavRoute.Comments.commentId),
        arguments = listOf(navArgument("comment_id") { type = NavType.StringType }),
    ) {
        CommentsScreen(hiltViewModel())
    }

    navGraphBuilder.composable(
        route = NavRoute.AssetDetail.withArgsFormat(NavRoute.AssetDetail.assetTicker),
        arguments = listOf(navArgument("asset_ticker") { type = NavType.StringType }),
    ) {
        AssetDetailScreen(hiltViewModel(), navController)
    }
}
