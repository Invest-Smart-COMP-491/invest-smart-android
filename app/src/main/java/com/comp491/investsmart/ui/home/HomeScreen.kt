package com.comp491.investsmart.ui.home

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
import com.comp491.investsmart.R
import com.comp491.investsmart.ui.common.NewsList
import com.comp491.investsmart.ui.theme.*

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    HomeScreenContent(
        uiState = viewModel.uiState.collectAsState().value,
        onNewsClicked = viewModel::onNewsClicked,
        onAssetTickerClicked = viewModel::onAssetTickerClicked,
    )
}

@Composable
private fun HomeScreenContent(
    uiState: HomeVMState,
    onNewsClicked: (String) -> Unit,
    onAssetTickerClicked: (String) -> Unit,
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
            StockPricesList(
                stockPrices = uiState.stockPrices,
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
                onAssetTickerClicked = onAssetTickerClicked,
            )
        }
    }
}

@Composable
private fun StockPricesList(
    stockPrices: List<Pair<String, Double>>,
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 25.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp),
    ) {
        stockPrices.forEachIndexed { index, pair ->
            item {
                StockPriceCard(
                    name = pair.first,
                    price = pair.second,
                    isFirst = index == 0,
                    isLast = index == stockPrices.size - 1,
                )
            }
        }
    }
}

@Composable
private fun StockPriceCard(
    name: String,
    price: Double,
    isFirst: Boolean,
    isLast: Boolean,
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
        ),
        elevation = 5.dp,
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 9.dp, vertical = 7.dp)
        ) {
            Text(
                text = name,
                fontFamily = montserratFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = Black,
            )
            Spacer(modifier = Modifier.width(25.dp))
            Text(
                text = if (price > 0) {
                    "+$price%"
                } else {
                    "$price%"
                },
                fontFamily = montserratFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = if(price >= 0) {
                    PriceGreen
                } else {
                    PriceRed
                },
            )
        }
    }
}
