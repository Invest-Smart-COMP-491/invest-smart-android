package com.comp491.investsmart.ui.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.comp491.investsmart.ui.common.CommentListType
import com.comp491.investsmart.ui.common.CommentsList
import com.comp491.investsmart.ui.theme.DarkGreen
import com.comp491.investsmart.ui.theme.montserratFamily

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    navController: NavController,
) {
    ProfileContent(
        uiState = viewModel.uiState.collectAsState().value,
        onLikeButtonClicked = viewModel::onLikeButtonClicked,
        onAnswerButtonClicked = { commentId ->
            viewModel.onAnswerButtonClicked(
                commentId = commentId,
                navController = navController,
            )
        },
        onAssetTickerClicked = viewModel::onAssetTickerClicked,
    )
}

@Composable
private fun ProfileContent(
    uiState: ProfileVMState,
    onLikeButtonClicked: (Int) -> Unit,
    onAnswerButtonClicked: (Int) -> Unit,
    onAssetTickerClicked: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 60.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = uiState.username,
            fontFamily = montserratFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            color = DarkGreen,
            modifier = Modifier.padding(top = 20.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        CommentsList(
            commentListType = CommentListType.PROFILE_PAGE,
            comments = uiState.comments,
            onLikeButtonClicked = onLikeButtonClicked,
            onAnswerButtonClicked = onAnswerButtonClicked,
            onAssetTickerClicked = onAssetTickerClicked,
        )
    }
}
