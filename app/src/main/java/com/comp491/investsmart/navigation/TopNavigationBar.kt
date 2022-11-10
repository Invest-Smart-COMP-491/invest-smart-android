package com.comp491.investsmart.navigation

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.comp491.investsmart.R
import com.comp491.investsmart.ui.theme.LightBlue

@Composable
fun TopNavigationBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute: String? = navBackStackEntry?.destination?.route
    val routesToShowBackButton = listOf(
        NavRoute.Settings.route,
    )
    val routesToShowSettingsIcon = listOf(
        NavRoute.Home.route,
        NavRoute.Search.route,
        NavRoute.Favourites.route,
        NavRoute.Profile.route,
    )

    TopAppBar(
        title = {},
        navigationIcon = {
            if (routesToShowBackButton.contains(currentRoute)) {
                IconButton(
                    onClick = {
                        navController.navigateUp()
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.back_icon),
                        contentDescription = null,
                    )
                }
            }
        },
        actions = {
            if (routesToShowSettingsIcon.contains(currentRoute)) {
                IconButton(
                    onClick = {
                        navController.navigate(NavRoute.Settings.route)
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.settings_icon),
                        contentDescription = null,
                    )
                }
            }
        },
        backgroundColor = Color.White,
        contentColor = LightBlue,
        elevation = 10.dp
    )
}
