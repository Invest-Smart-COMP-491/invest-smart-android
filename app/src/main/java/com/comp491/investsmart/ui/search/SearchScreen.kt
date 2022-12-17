package com.comp491.investsmart.ui.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.comp491.investsmart.R
import com.comp491.investsmart.ui.common.AssetList
import com.comp491.investsmart.ui.theme.*

@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    navController: NavController,
) {
    SearchScreenContent(
        uiState = viewModel.uiState.collectAsState().value,
        onSearchRequested = viewModel::onSearchRequested,
        onAssetClicked = { assetTicker ->
            viewModel.onAssetClicked(
                assetTicker = assetTicker,
                navController = navController,
            )
        },
    )
}

@Composable
private fun SearchScreenContent(
    uiState: SearchVMState,
    onAssetClicked: (String) -> Unit,
    onSearchRequested: (String) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    var textFieldValue by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 45.dp)
    ) {
        TextField(
            value = textFieldValue,
            onValueChange = {
                textFieldValue = it
            },
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.search_icon),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = Gray,
                )
            },
            shape = RoundedCornerShape(15.dp),
            colors = TextFieldDefaults.textFieldColors(
                textColor = Gray,
                backgroundColor = VeryLightBlue,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            placeholder = {
                Text(
                    text = stringResource(id = R.string.search_bar_label),
                    fontFamily = montserratFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = Gray,
                )
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    onSearchRequested(textFieldValue)
                    focusManager.clearFocus()
                }
            )
        )

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
            AssetList(
                assets = uiState.assets,
                onAssetClicked = onAssetClicked,
                modifier = Modifier.padding(top = 20.dp, bottom = 60.dp),
            )
        }
    }
}
