package com.comp491.investsmart.ui.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.comp491.investsmart.R
import com.comp491.investsmart.ui.theme.Black
import com.comp491.investsmart.ui.theme.DarkGreen
import com.comp491.investsmart.ui.theme.Gray
import com.comp491.investsmart.ui.theme.montserratFamily

@Composable
fun SettingsScreen(viewModel: SettingsViewModel) {
    SettingsScreenContent(
        uiState = viewModel.uiState.collectAsState().value,
        onChangePasswordButtonClicked = viewModel::onChangePasswordButtonClicked,
        onLogOutButtonClicked = viewModel::onLogOutButtonClicked,
    )
}

@Composable
private fun SettingsScreenContent(
    uiState: SettingsVMState,
    onChangePasswordButtonClicked: () -> Unit,
    onLogOutButtonClicked: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 15.dp)
    ) {
        SettingRow(
            title = stringResource(id = R.string.settings_username),
            text = uiState.username,
        )
        Spacer(modifier = Modifier.height(25.dp))
        SettingRow(
            title = stringResource(id = R.string.settings_email),
            text = uiState.email,
        )
        Spacer(modifier = Modifier.height(25.dp))
/*
        SettingRow(
            title = stringResource(id = R.string.settings_password),
            text = stringResource(id = R.string.change_password_button),
            onClick = onChangePasswordButtonClicked
        )
        Spacer(modifier = Modifier.height(25.dp))
*/
        LogOutButton(
            text = stringResource(id = R.string.log_out_button),
            onClick = onLogOutButtonClicked,
        )
    }
}

@Composable
private fun SettingRow(
    title: String,
    text: String,
    onClick: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .clickable { onClick() }
            .fillMaxWidth(),
    ) {
        Text(
            text = title,
            fontFamily = montserratFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            color = Black,
        )
        Spacer(modifier = Modifier.height(3.dp))
        Text(
            text = text,
            fontFamily = montserratFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            color = Gray,
        )
    }
}

@Composable
private fun LogOutButton(
    text: String,
    onClick: () -> Unit = {},
) {
    Text(
        text = text,
        fontFamily = montserratFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        color = DarkGreen,
        modifier = Modifier.clickable { onClick() }
    )
}
