package com.comp491.investsmart.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.comp491.investsmart.ui.theme.PriceGreen
import com.comp491.investsmart.ui.theme.PriceRed
import com.comp491.investsmart.ui.theme.montserratFamily
import com.comp491.investsmart.R
import com.comp491.investsmart.ui.theme.DarkGreen
import com.comp491.investsmart.ui.theme.Black

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    HomeScreenContent(
        uiState = viewModel.uiState.collectAsState().value,
        onNewsClicked = viewModel::onNewsClicked,
    )
}

@Composable
private fun HomeScreenContent(
    uiState: HomeVMState,
    onNewsClicked: (Int) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
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
        )
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

@Composable
private fun NewsList(
    news: List<String>,
    onNewsClicked: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier
    ) {
        news.forEachIndexed { index, n ->
            item {
                NewsRow(
                    title = "Facebook parent company Meta will lay off 11,000 employees",
                    text = "The job cuts come as Meta confronts a range of challenges to its core business and makes an uncertain and costly bet on pivoting to the metaverse. It also comes amid a spate of layoffs at other tech firms in recent months as the high-flying sector reacts to high inflation, rising interest rates and fears of a looming recession.",
                    onNewsClicked = { onNewsClicked(index) },
                )
            }

            if (index != news.size - 1) {
                item {
                    Divider(
                        color = Black,
                        thickness = 1.dp,
                    )
                }
            }
        }
    }
}

@Composable
private fun NewsRow(
    title: String,
    text: String,
    onNewsClicked: () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 15.dp)
            .clickable { onNewsClicked() },
    ) {
        Text(
            text = title,
            fontFamily = montserratFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 15.sp,
            color = Black,
        )
        Spacer(modifier = Modifier.height(25.dp))
        Text(
            text = text,
            fontFamily = montserratFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            color = Black,
        )
    }
}
