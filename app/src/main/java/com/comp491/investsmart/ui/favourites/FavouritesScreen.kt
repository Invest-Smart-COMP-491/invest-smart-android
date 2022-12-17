package com.comp491.investsmart.ui.favourites

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.comp491.investsmart.ui.common.AssetList
import com.comp491.investsmart.ui.theme.LightBlue

@Composable
fun FavouritesScreen(
    viewModel: FavouritesViewModel,
    navController: NavController,
) {
    FavouritesScreenContent(
        uiState = viewModel.uiState.collectAsState().value,
        onAssetClicked = { assetTicker ->
            viewModel.onAssetClicked(
                assetTicker = assetTicker,
                navController = navController,
            )
        }
    )
}

@Composable
private fun FavouritesScreenContent(
    uiState: FavouritesVMState,
    onAssetClicked: (String) -> Unit,
) {
    if (uiState.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(40.dp),
                color = LightBlue,
                strokeWidth = 5.dp
            )
        }
    } else {
        AssetList(
            assets = uiState.assets,
            onAssetClicked = onAssetClicked,
            modifier = Modifier
                .fillMaxSize()
                .padding(top  = 20.dp, bottom = 60.dp),
        )
    }
}
