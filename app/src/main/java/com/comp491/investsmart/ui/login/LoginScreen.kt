package com.comp491.investsmart.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.comp491.investsmart.R
import com.comp491.investsmart.ui.theme.*

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    navController: NavController,
) {

    LoginContent(
        uiState = viewModel.uiState.collectAsState().value,
        onSignUpButtonClicked = viewModel::onSignUpButtonClicked,
        onSignInButtonClicked = viewModel::onSignInButtonClicked,
        onAlreadyHaveAnAccountButtonClicked = viewModel::onAlreadyHaveAnAccountButtonClicked,
        onDontHaveAnAccountButtonClicked = viewModel::onDontHaveAnAccountButtonClicked,
        navController = navController,
    )
}

@Composable
private fun LoginContent(
    uiState: LoginVMState,
    onSignUpButtonClicked: (String, String, String, NavController) -> Unit,
    onSignInButtonClicked: (String, String, NavController) -> Unit,
    onAlreadyHaveAnAccountButtonClicked: () -> Unit,
    onDontHaveAnAccountButtonClicked: () -> Unit,
    navController: NavController,
) {
    val focusManager = LocalFocusManager.current
    var usernameTextFieldValue by remember { mutableStateOf("") }
    var emailTextFieldValue by remember { mutableStateOf("") }
    var passwordTextFieldValue by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.login_screen_logo),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 10.dp)
                .size(100.dp)
        )

        LoginTextField(
            focusManager = focusManager,
            label = {
                Text(
                    text = stringResource(id = R.string.username_label),
                    fontFamily = montserratFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = Gray,
                )
            },
            value = usernameTextFieldValue,
            onValueChange = { newValue ->
                usernameTextFieldValue = newValue
            }
        )

        Spacer(modifier = Modifier.height(15.dp))

        if (uiState.loginType == LoginType.SIGN_UP) {
            LoginTextField(
                focusManager = focusManager,
                label = {
                    Text(
                        text = stringResource(id = R.string.email_label),
                        fontFamily = montserratFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        color = Gray,
                    )
                },
                value = emailTextFieldValue,
                onValueChange = { newValue ->
                    emailTextFieldValue = newValue
                }
            )

            Spacer(modifier = Modifier.height(15.dp))
        }

        LoginTextField(
            focusManager = focusManager,
            label = {
                Text(
                    text = stringResource(id = R.string.password_label),
                    fontFamily = montserratFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = Gray,
                )
            },
            value = passwordTextFieldValue,
            onValueChange = { newValue ->
                passwordTextFieldValue = newValue
            },
            isPassword = true,
        )
        
        Spacer(modifier = Modifier.height(30.dp))

        when (uiState.loginType) {
            LoginType.SIGN_IN -> {
                Text(
                    text = stringResource(id = R.string.dont_have_an_account_label),
                    fontFamily = montserratFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = Black,
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .clickable {
                            onDontHaveAnAccountButtonClicked()
                        }
                )

                LoginButton(
                    text = stringResource(id = R.string.log_in_button),
                    onClick = {
                        onSignInButtonClicked(
                            usernameTextFieldValue,
                            passwordTextFieldValue,
                            navController
                        )
                    },
                )
            }
            LoginType.SIGN_UP -> {
                Text(
                    text = stringResource(id = R.string.already_have_an_account_label),
                    fontFamily = montserratFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = Black,
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .clickable {
                            onAlreadyHaveAnAccountButtonClicked()
                        }
                )

                LoginButton(
                    text = stringResource(id = R.string.sign_up_button),
                    onClick = {
                        onSignUpButtonClicked(
                            usernameTextFieldValue,
                            emailTextFieldValue,
                            passwordTextFieldValue,
                            navController
                        )
                    },
                )
            }
        }
    }
}

@Composable
private fun LoginTextField(
    focusManager: FocusManager,
    label: @Composable () -> Unit,
    value: String,
    onValueChange: (String) -> Unit,
    isPassword: Boolean = false,
) {
    TextField(
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        colors = TextFieldDefaults.textFieldColors(
            textColor = Black,
            backgroundColor = White,
            focusedIndicatorColor = DarkGreen,
            unfocusedIndicatorColor = Gray,
            disabledIndicatorColor = Gray,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 40.dp),
        label = label,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
            }
        ),
        visualTransformation = if (isPassword) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
    )
}

@Composable
private fun LoginButton(
    text: String,
    onClick: () -> Unit,
) {
    Button(onClick = onClick) {
        Text(
            text = text,
            fontFamily = montserratFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            color = White,
        )
    }
}
