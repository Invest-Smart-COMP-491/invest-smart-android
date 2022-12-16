package com.comp491.investsmart.ui.assetdetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun AssetDetailScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "Asset Detail Screen", modifier = Modifier.align(Alignment.Center))
    }
}
