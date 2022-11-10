package com.comp491.investsmart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.comp491.investsmart.navigation.BottomNavigationBar
import com.comp491.investsmart.navigation.NavGraph
import com.comp491.investsmart.navigation.TopNavigationBar
import com.comp491.investsmart.ui.theme.InvestSmartTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
private fun MainScreen() {
    InvestSmartTheme {
        val navController = rememberNavController()
        Scaffold(
            topBar = { TopNavigationBar(navController = navController) },
            bottomBar = { BottomNavigationBar(navController = navController) }
        ) {

            NavGraph(navController = navController)
        }
    }
}
