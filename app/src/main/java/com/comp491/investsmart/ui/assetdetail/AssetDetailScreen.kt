package com.comp491.investsmart.ui.assetdetail

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.comp491.investsmart.data.api.Constant
import com.comp491.investsmart.ui.webpage.WebPageViewModel

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun AssetDetailScreen(
    viewModel: AssetDetailViewModel,
    navController: NavController,
) {
    val url = Constant.baseUrl + "plot/"+ viewModel.uiState.collectAsState().value.asset.assetTicker

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 60.dp),
    ){
        Box(modifier = Modifier) {
            Text(text = viewModel.uiState.collectAsState().value.asset.assetName, modifier = Modifier.align(Alignment.Center))
        }

        AndroidView(
            factory = {
                WebView(it).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    settings.javaScriptEnabled = true
                    settings.javaScriptCanOpenWindowsAutomatically = true
                    webViewClient = WebViewClient()
                    loadUrl(url)
                }
            }, update = {
                it.loadUrl(url)
            }
        )
    }



}
