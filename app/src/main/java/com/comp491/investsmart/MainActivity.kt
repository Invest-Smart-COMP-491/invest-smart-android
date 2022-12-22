package com.comp491.investsmart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.comp491.investsmart.data.datastore.DataStoreManager
import com.comp491.investsmart.navigation.BottomNavigationBar
import com.comp491.investsmart.navigation.NavGraph
import com.comp491.investsmart.navigation.NavRoute
import com.comp491.investsmart.navigation.TopNavigationBar
import com.comp491.investsmart.ui.theme.InvestSmartTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity() : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       lifecycleScope.launch {
           val token = DataStoreManager(appContext = applicationContext).token.first()

           val startDestination = if (token == "") {
               NavRoute.Login.route
           } else {
               NavRoute.Home.route
           }

           setContent {
               MainScreen(startDestination = startDestination)
           }
        }
    }
}

@Composable
private fun MainScreen(
    startDestination: String,
) {

    val routesToShowBottomNavigationBar = listOf(
        NavRoute.Home.route,
        NavRoute.Search.route,
        NavRoute.Favourites.route,
        NavRoute.Profile.route,
    )

    InvestSmartTheme {
        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute: String? = navBackStackEntry?.destination?.route

        Scaffold(
            topBar = { TopNavigationBar(navController = navController) },
            bottomBar = {
                if (routesToShowBottomNavigationBar.contains(currentRoute)) {
                    BottomNavigationBar(navController = navController)
                }
            }
        ) {

            NavGraph(
                navController = navController,
                startDestination = startDestination,
            )
        }
    }
}
