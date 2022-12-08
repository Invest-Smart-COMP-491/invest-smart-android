package com.comp491.investsmart.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.comp491.investsmart.data.api.retrofit.InvestSmartService
import com.comp491.investsmart.domain.news.usecases.GetAllNewsUseCase
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
    private val investSmartService: InvestSmartService,
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
            val a = investSmartService.listPost("1")

            Log.d("damla", a.body().toString())
        }
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
