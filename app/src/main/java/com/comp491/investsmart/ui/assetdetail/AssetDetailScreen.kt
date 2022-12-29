package com.comp491.investsmart.ui.assetdetail

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.comp491.investsmart.R
import com.comp491.investsmart.domain.assets.entities.Asset
import com.comp491.investsmart.ui.common.CommentList
import com.comp491.investsmart.ui.common.CommentListType
import com.comp491.investsmart.ui.common.NewsList
import com.comp491.investsmart.ui.theme.*

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun AssetDetailScreen(
    viewModel: AssetDetailViewModel,
    navController: NavController,
) {
    AssetDetailContent(
        uiState = viewModel.uiState.collectAsState().value,
        onNewsClicked = { url ->
            viewModel.onNewsClicked(
                url = url,
                navController = navController,
            )
        },
        onCommentLikeButtonClicked = {id -> viewModel.onCommentLikeButtonClicked(commentId = id)},
        onAssetFavouriteButtonClicked = viewModel::onAssetFavouriteButtonClicked,
        onPageTypeChanged = viewModel::onPageTypeChanged,
        onPriceGraphButtonClicked = {
            viewModel.onPriceGraphButtonClicked(
                navController = navController,
            )
        },
        onSendClicked = viewModel::onSendClicked,
        onAnswerButtonClicked = { commentId ->
            viewModel.onAnswerButtonClicked(
                commentId = commentId,
                navController = navController,
            )
        }
    )
}

@Composable
fun AssetDetailContent(
    uiState: AssetDetailVMState,
    onNewsClicked: (String) -> Unit,
    onCommentLikeButtonClicked: (Int) -> Unit,
    onAssetFavouriteButtonClicked: () -> Unit,
    onPageTypeChanged: (PageType) -> Unit,
    onPriceGraphButtonClicked: () -> Unit,
    onSendClicked: (String) -> Unit,
    onAnswerButtonClicked: (Int) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    var textFieldValue by remember { mutableStateOf("") }

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
                .padding(horizontal = 15.dp),
        ) {
            AssetDetails(
                uiState = uiState,
                asset = uiState.asset,
                pageType = uiState.pageType,
                onAssetFavouriteButtonClicked = onAssetFavouriteButtonClicked,
                onPageTypeChanged = onPageTypeChanged,
                onPriceGraphButtonClicked = onPriceGraphButtonClicked,
            )

            when (uiState.pageType) {
                PageType.NEWS -> {
                    NewsList(
                        news = uiState.assetNews,
                        onNewsClicked = onNewsClicked,
                        onAssetTickerClicked = { },
                    )
                }
                PageType.COMMENTS -> {
                    OutlinedTextField(
                        value = textFieldValue,
                        onValueChange = {
                            textFieldValue = it
                        },
                        trailingIcon = {
                            Text(
                                text = stringResource(id = R.string.send_button),
                                fontFamily = montserratFamily,
                                fontWeight = FontWeight.Normal,
                                fontSize = 14.sp,
                                color = Gray,
                                modifier = Modifier
                                    .padding(end = 15.dp)
                                    .clickable { onSendClicked(textFieldValue) }
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        placeholder = {
                            Text(
                                text = stringResource(id = R.string.comments_answer_label),
                                fontFamily = montserratFamily,
                                fontWeight = FontWeight.Normal,
                                fontSize = 14.sp,
                                color = Gray,
                                modifier = Modifier.padding(start = 10.dp)
                            )
                        },
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                focusManager.clearFocus()
                            }
                        )
                    )

                    CommentList(
                        commentListType = CommentListType.ASSET_DETAIL_PAGE,
                        comments = uiState.assetComments,
                        likedComments = uiState.likedComments,
                        onLikeButtonClicked = onCommentLikeButtonClicked,
                        onAnswerButtonClicked = onAnswerButtonClicked,
                    )
                }
            }
        }
    }
}

@Composable
private fun AssetDetails(
    uiState: AssetDetailVMState,
    asset: Asset,
    pageType: PageType,
    onAssetFavouriteButtonClicked: () -> Unit,
    onPageTypeChanged: (PageType) -> Unit,
    onPriceGraphButtonClicked: () -> Unit,
) {
    var isAssetInFavourites by remember { mutableStateOf(uiState.isFollowed) }

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp, bottom = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                modifier = Modifier
                    .weight(0.9F)
                    .padding(end = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter =  rememberAsyncImagePainter(model =  asset.photoUrl),
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
                        text = stringResource(
                            id = R.string.asset_price, String.format("%.2f", asset.lastPrice)
                        ),
                        fontFamily = montserratFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        color = Black,
                    )
                }
            }

            Icon(
                painter = painterResource(
                    id = if (isAssetInFavourites) {
                        R.drawable.blue_like_icon
                    } else {
                        R.drawable.like_icon
                    }
                ),
                contentDescription = null,
                modifier = Modifier
                    .weight(0.1F)
                    .padding(end = 10.dp)
                    .size(20.dp)
                    .clickable {
                        isAssetInFavourites = !isAssetInFavourites
                        onAssetFavouriteButtonClicked()
                    }
            )
        }

        Row(
            modifier = Modifier
                .padding(bottom = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            SectionButton(
                onClick = {
                    onPageTypeChanged(PageType.NEWS)
                },
                stringId = R.string.news_button,
                isSelected = pageType == PageType.NEWS,
            )

            SectionButton(
                onClick = {
                    onPageTypeChanged(PageType.COMMENTS)
                },
                stringId = R.string.comments_button,
                isSelected = pageType == PageType.COMMENTS,
            )

            SectionButton(
                onClick = onPriceGraphButtonClicked,
                stringId = R.string.prices_button,
                isSelected = false,
            )
        }
    }
}

@Composable
private fun SectionButton(
    onClick: () -> Unit,
    stringId: Int,
    isSelected: Boolean,
) {
    Card(
        modifier = Modifier.clickable {
            onClick()
        },
        elevation = 5.dp,
        shape = RoundedCornerShape(8.dp),
        backgroundColor = if (isSelected) {
            DarkGreen
        } else {
            White
        },
    ) {
        Text(
            text = stringResource(id = stringId),
            fontFamily = montserratFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            color = if (isSelected) {
                White
            } else {
                DarkGreen
            },
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 7.dp),
        )
    }
}
