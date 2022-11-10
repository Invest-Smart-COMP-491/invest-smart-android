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
    TopAppBar(
        title = {},
        navigationIcon = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute: String? = navBackStackEntry?.destination?.route
            val routesToShowBackButton = emptyList<String>()

            if (routesToShowBackButton.contains(currentRoute)) {
                IconButton(
                    onClick = {
                        navController.navigateUp()
                        navController.popBackStack()
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
        },
        backgroundColor = Color.White,
        contentColor = LightBlue,
        elevation = 10.dp
    )
}
