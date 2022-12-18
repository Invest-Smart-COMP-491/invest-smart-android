package com.comp491.investsmart.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.comp491.investsmart.R
import com.comp491.investsmart.domain.assets.entities.Asset
import com.comp491.investsmart.ui.common.NewsList
import com.comp491.investsmart.ui.theme.*

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    navController: NavController,
) {
    HomeScreenContent(
        uiState = viewModel.uiState.collectAsState().value,
        onNewsClicked = {url ->
            viewModel.onNewsClicked(
                url = url,
                navController = navController,
            )
        },
        onAssetClicked = { assetTicker ->
            viewModel.onAssetClicked(
                assetTicker = assetTicker,
                navController = navController,
            )
        },
    )
}

@Composable
private fun HomeScreenContent(
    uiState: HomeVMState,
    onNewsClicked: (String) -> Unit,
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 60.dp),
        ) {
            TrendingAssetsList(
                trendingAssets = uiState.trendingAssets,
                onAssetClicked = onAssetClicked,
            )

            Text(
                text = stringResource(id = R.string.daily_news_title),
                fontFamily = montserratFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                color = DarkGreen,
                modifier = Modifier.padding(start = 20.dp)
            )

            NewsList(
                news = uiState.news,
                onNewsClicked = onNewsClicked,
                onAssetTickerClicked = onAssetClicked,
            )
        }
    }
}

@Composable
private fun TrendingAssetsList(
    trendingAssets: List<Asset>,
    onAssetClicked: (String) -> Unit,
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 25.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp),
    ) {
        trendingAssets.forEachIndexed { index, asset ->
            item {
                StockPriceCard(
                    asset = asset,
                    isFirst = index == 0,
                    isLast = index == trendingAssets.size - 1,
                    onAssetClicked = onAssetClicked,
                )
            }
        }
    }
}

@Composable
private fun StockPriceCard(
    asset: Asset,
    isFirst: Boolean,
    isLast: Boolean,
    onAssetClicked: (String) -> Unit,
) {
    Card(
        modifier = Modifier.padding(
            start = if (isFirst) {
                20.dp
            } else {
                0.dp
            },
            end = if (isLast) {
                20.dp
            } else {
                0.dp
            }
        ).clickable {
            onAssetClicked(asset.assetTicker)
        },
        elevation = 5.dp,
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 9.dp, vertical = 7.dp)
        ) {
            Text(
                text = asset.assetName,
                fontFamily = montserratFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = Black,
            )
            Spacer(modifier = Modifier.width(25.dp))
            Text(
                text = stringResource(id = R.string.asset_price,
                        String.format("%.2f", asset.lastPrice)),
                fontFamily = montserratFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
            )
        }
    }
}
