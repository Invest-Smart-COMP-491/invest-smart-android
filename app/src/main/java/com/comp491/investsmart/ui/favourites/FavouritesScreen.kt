package com.comp491.investsmart.ui.favourites

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.comp491.investsmart.ui.common.AssetList

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
    AssetList(
        assets = uiState.assets,
        onAssetClicked = onAssetClicked,
        modifier = Modifier
            .fillMaxSize()
            .padding(top  = 20.dp, bottom = 60.dp),
    )
}
