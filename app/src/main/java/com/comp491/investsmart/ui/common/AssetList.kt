package com.comp491.investsmart.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.comp491.investsmart.domain.assets.entities.Asset
import com.comp491.investsmart.ui.theme.Black
import com.comp491.investsmart.ui.theme.montserratFamily
import com.comp491.investsmart.R

@Composable
fun AssetList(
    assets: List<Asset>,
    onAssetClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .padding(horizontal = 20.dp),
    ) {
        assets.forEach { asset ->
            item {
                AssetRow(
                    asset = asset,
                    onAssetClicked = onAssetClicked,
                )
            }
        }
    }
}

@Composable
private fun AssetRow(
    asset: Asset,
    onAssetClicked: (String) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onAssetClicked(asset.assetTicker) },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter =  rememberAsyncImagePainter(
                    model = asset.photoUrl,
                    // TODO: add placeholder while loading or in case of an error.
                ),
                contentDescription = null,
                modifier = Modifier
                    .size(70.dp)
                    .padding(end = 15.dp),
            )
            Column {
                Text(
                    text = asset.assetName,
                    fontFamily = montserratFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = Black,
                )
                Text(
                    text = stringResource(id = R.string.asset_followers, asset.viewCount),
                    fontFamily = montserratFamily,
                    fontWeight = FontWeight.Light,
                    fontSize = 10.sp,
                    color = Black,
                )
            }
        }

        Text(
            text = stringResource(id = R.string.asset_price, String.format("%.2f", asset.lastPrice)),
            fontFamily = montserratFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 13.sp,
            color = Black,
        )
    }
}
