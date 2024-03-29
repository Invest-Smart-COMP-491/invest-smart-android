package com.comp491.investsmart.navigation

import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.comp491.investsmart.R
import com.comp491.investsmart.ui.theme.DarkGreen
import com.comp491.investsmart.ui.theme.LightBlue

@Composable
fun BottomNavigationBar(navController: NavController) {

    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Search,
        BottomNavItem.Favourites,
        BottomNavItem.Profile,
    )

    BottomNavigation(
        backgroundColor = Color.White,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconId),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                    )
                },
                selectedContentColor = DarkGreen,
                unselectedContentColor = LightBlue,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        // Avoid multiple copies of the same destination when re-selecting the same item
                        launchSingleTop = true
                        // Restore state when re-selecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }
}

sealed class BottomNavItem(
    val route: String,
    val iconId: Int,
) {
    object Home : BottomNavItem(
        route = NavRoute.Home.route,
        iconId = R.drawable.home_icon,
    )

    object Search : BottomNavItem(
        route = NavRoute.Search.route,
        iconId = R.drawable.search_icon,
    )

    object Favourites : BottomNavItem(
        route = NavRoute.Favourites.route,
        iconId = R.drawable.favourites_icon,
    )

    object Profile : BottomNavItem(
        route = NavRoute.Profile.route,
        iconId = R.drawable.profile_icon,
    )
}
