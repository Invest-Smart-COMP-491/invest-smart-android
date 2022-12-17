package com.comp491.investsmart.ui.comments

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.comp491.investsmart.R
import com.comp491.investsmart.ui.common.CommentListType
import com.comp491.investsmart.ui.common.CommentList
import com.comp491.investsmart.ui.theme.*

@Composable
fun CommentsScreen(
    viewModel: CommentsViewModel,
) {
    CommentsContent(
        uiState = viewModel.uiState.collectAsState().value,
        onLikeButtonClicked = viewModel::onLikeButtonClicked,
        onUsernameClicked = viewModel::onUsernameClicked,
        onReplyClicked = viewModel::onReplyClicked,
    )
}

@Composable
private fun CommentsContent(
    uiState: CommentsVMState,
    onLikeButtonClicked: (Int) -> Unit,
    onUsernameClicked: (Int) -> Unit,
    onReplyClicked: (String) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    var textFieldValue by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OutlinedTextField(
            value = textFieldValue,
            onValueChange = {
                textFieldValue = it
            },
            trailingIcon = {
                Text(
                    text = stringResource(id = R.string.comments_reply),
                    fontFamily = montserratFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = Gray,
                    modifier = Modifier
                        .padding(end = 15.dp)
                        .clickable { onReplyClicked(textFieldValue) }
                )
            },
            modifier = Modifier
                .fillMaxWidth(),
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
            commentListType = CommentListType.COMMENTS_PAGE,
            comments = uiState.comments,
            onLikeButtonClicked = onLikeButtonClicked,
            onUsernameClicked = onUsernameClicked,
        )
    }
}
