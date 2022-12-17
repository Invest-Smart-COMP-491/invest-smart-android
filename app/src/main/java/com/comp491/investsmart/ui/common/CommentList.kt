package com.comp491.investsmart.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.comp491.investsmart.R
import com.comp491.investsmart.domain.comments.entities.Comment
import com.comp491.investsmart.ui.theme.Black
import com.comp491.investsmart.ui.theme.montserratFamily

@Composable
fun CommentList(
    commentListType: CommentListType,
    comments: List<Comment>,
    onLikeButtonClicked: (Int) -> Unit,
    onAnswerButtonClicked: (Int) -> Unit = {},
    onUsernameClicked: (Int) -> Unit = {},
    onAssetTickerClicked: (String) -> Unit = {},
) {
    LazyColumn {
        comments.forEachIndexed { index, comment ->
            item {
                CommentsRow(
                    commentListType = commentListType,
                    comment = comment,
                    onLikeButtonClicked = onLikeButtonClicked,
                    onAnswerButtonClicked = onAnswerButtonClicked,
                    onUsernameClicked = onUsernameClicked,
                    onAssetTickerClicked = onAssetTickerClicked,
                )
            }

            if (index != comments.size - 1) {
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
fun CommentsRow(
    commentListType: CommentListType,
    comment: Comment,
    onLikeButtonClicked: (Int) -> Unit,
    onAnswerButtonClicked: (Int) -> Unit,
    onUsernameClicked: (Int) -> Unit,
    onAssetTickerClicked: (String) -> Unit,
) {
    var likedBefore by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 15.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            when (commentListType) {
                CommentListType.ASSET_DETAIL_PAGE,
                CommentListType.COMMENTS_PAGE -> {
                    Text(
                        text = comment.username,
                        fontFamily = montserratFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp,
                        color = Black,
                        modifier = Modifier.clickable { onUsernameClicked(comment.userId) }
                    )
                }
                CommentListType.PROFILE_PAGE -> {
                    Text(
                        text = comment.assetTicker,
                        fontFamily = montserratFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp,
                        color = Black,
                        modifier = Modifier.clickable { onAssetTickerClicked(comment.assetTicker) }
                    )
                }
            }

            Text(
                text = comment.date,
                fontFamily = montserratFamily,
                fontWeight = FontWeight.Light,
                fontSize = 10.sp,
                color = Black,
            )
        }

        Spacer(modifier = Modifier.height(5.dp))

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = comment.text,
            fontFamily = montserratFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            color = Black,
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
        ) {
            Icon(
                painter = painterResource(
                    id = if (likedBefore) {
                        R.drawable.blue_like_icon
                    } else {
                        R.drawable.like_icon
                    }
                ),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 5.dp)
                    .size(20.dp)
                    .clickable {
                        likedBefore = !likedBefore
                        onLikeButtonClicked(comment.id) }
            )

            Text(
                text = comment.likeCount.toString(),
                fontFamily = montserratFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                color = Black,
            )

            if (commentListType != CommentListType.COMMENTS_PAGE){
                Spacer(modifier = Modifier.width(25.dp))

                Icon(
                    painter = painterResource(id = R.drawable.comment_icon),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 5.dp)
                        .size(20.dp)
                        .clickable {
                            onAnswerButtonClicked(comment.id)
                        }
                )

                Text(
                    text = comment.answerCount.toString(),
                    fontFamily = montserratFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    color = Black,
                )
            }
        }
    }
}

enum class CommentListType {
    PROFILE_PAGE,
    ASSET_DETAIL_PAGE,
    COMMENTS_PAGE,
}
