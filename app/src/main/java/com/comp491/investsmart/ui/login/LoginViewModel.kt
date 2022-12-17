package com.comp491.investsmart.ui.login

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.comp491.investsmart.navigation.NavRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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

        // On Success
        navController.navigate(NavRoute.Home.route) {
            popUpTo(NavRoute.Login.route) {
                inclusive = true
            }
        }
    }

    fun onSignInButtonClicked(
        email: String,
        password: String,
        navController: NavController,
    ) {

        // On Success
        navController.navigate(NavRoute.Home.route) {
            popUpTo(NavRoute.Login.route) {
                inclusive = true
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
