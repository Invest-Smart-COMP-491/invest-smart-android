package com.comp491.investsmart.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.comp491.investsmart.domain.news.entities.News
import com.comp491.investsmart.ui.theme.Black
import com.comp491.investsmart.ui.theme.montserratFamily

@Composable
fun NewsList(
    news: List<News>,
    onNewsClicked: (String) -> Unit,
    onAssetTickerClicked: (String) -> Unit,
) {
    LazyColumn() {
        news.forEachIndexed { index, n ->
            item {
                NewsRow(
                    news = n,
                    onNewsClicked = onNewsClicked,
                    onAssetTickerClicked = onAssetTickerClicked,
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
    news: News,
    onNewsClicked: (String) -> Unit,
    onAssetTickerClicked: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 15.dp)
            .clickable { onNewsClicked(news.url) },
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                news.thumbnail?.let {
                    Image(
                        painter =  rememberAsyncImagePainter(
                            model = it,
                            // TODO: add placeholder while loading or in case of an error.
                        ),
                        contentDescription = null,
                        modifier = Modifier
                            .size(70.dp)
                            .padding(end = 15.dp),
                    )
                }
                Column {
                    Text(
                        text = news.publisher,
                        fontFamily = montserratFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = Black,
                    )
                    Text(
                        text = news.publishedDate,
                        fontFamily = montserratFamily,
                        fontWeight = FontWeight.Light,
                        fontSize = 10.sp,
                        color = Black,
                    )
                }
            }

            Text(
                text = news.assetTicker,
                fontFamily = montserratFamily,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp,
                color = Black,
                modifier = Modifier.clickable {
                    onAssetTickerClicked(news.assetTicker)
                },
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = news.title,
            fontFamily = montserratFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp,
            color = Black,
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = news.summary,
            fontFamily = montserratFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            color = Black,
        )
    }
}
