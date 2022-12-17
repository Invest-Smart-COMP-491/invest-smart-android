package com.comp491.investsmart.ui.favourites

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.comp491.investsmart.ui.common.AssetList

@Composable
fun FavouritesScreen(
    viewModel: FavouritesViewModel,
) {
    FavouritesScreenContent(
        uiState = viewModel.uiState.collectAsState().value,
    )
}

@Composable
private fun FavouritesScreenContent(
    uiState: FavouritesVMState,
) {
    AssetList(
        assets = uiState.assets,
        modifier = Modifier
            .fillMaxSize()
            .padding(top  = 20.dp, bottom = 60.dp),
    )
}
