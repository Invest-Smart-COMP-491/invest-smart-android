package com.comp491.investsmart.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.comp491.investsmart.domain.users.usecases.SignInUseCase
import com.comp491.investsmart.domain.users.usecases.SignUpUseCase
import com.comp491.investsmart.data.api.Result
import com.comp491.investsmart.navigation.NavRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class LoginType {
    SIGN_IN,
    SIGN_UP,
}

data class LoginVMState(
    val loginType: LoginType,
)

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val signInUseCase: SignInUseCase,
) : ViewModel() {

    private val vmState = LoginVMState(
        loginType = LoginType.SIGN_UP,
    )
    private val _vmState = MutableStateFlow(vmState)
    val uiState: StateFlow<LoginVMState> = _vmState.asStateFlow()

    fun onSignUpButtonClicked(
        username: String,
        email: String,
        password: String,
        navController: NavController,
    ) {
        viewModelScope.launch {
            val result = signUpUseCase(
                username = username,
                email = email,
                password = password,
            )

            if (result is Result.Success) {
                navController.navigate(NavRoute.Home.route) {
                    popUpTo(NavRoute.Login.route) {
                        inclusive = true
                    }
                }
            } else {
                // TODO: show an error dialog.
            }
        }
    }

    fun onSignInButtonClicked(
        username: String,
        password: String,
        navController: NavController,
    ) {

        viewModelScope.launch {
            val result = signInUseCase(
                username = username,
                password = password,
            )

            if (result is Result.Success) {
                navController.navigate(NavRoute.Home.route) {
                    popUpTo(NavRoute.Login.route) {
                        inclusive = true
                    }
                }
            } else {
                // TODO: show an error dialog.
            }
        }
    }

    fun onAlreadyHaveAnAccountButtonClicked() {
        _vmState.value = LoginVMState(
            loginType = LoginType.SIGN_IN,
        )
    }

    fun onDontHaveAnAccountButtonClicked() {
        _vmState.value = LoginVMState(
            loginType = LoginType.SIGN_UP,
        )
    }
}
