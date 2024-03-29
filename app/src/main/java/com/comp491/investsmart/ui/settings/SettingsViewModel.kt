package com.comp491.investsmart.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.comp491.investsmart.data.api.Result
import com.comp491.investsmart.domain.users.entities.UserInfoType
import com.comp491.investsmart.domain.users.usecases.GetUserInfoUseCase
import com.comp491.investsmart.domain.users.usecases.LogOutUseCase
import com.comp491.investsmart.navigation.NavRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SettingsVMState(
    val username: String,
    val email: String,
)

@HiltViewModel
class SettingsViewModel  @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val logOutUseCase: LogOutUseCase,
) : ViewModel() {

    private val vmState = SettingsVMState(
        username = "",
        email = ""
    )
    private val _vmState = MutableStateFlow(vmState)
    val uiState: StateFlow<SettingsVMState> = _vmState.asStateFlow()

    init {
        viewModelScope.launch {
            _vmState.value = SettingsVMState(
                username =  getUserInfoUseCase(UserInfoType.USERNAME),
                email =  getUserInfoUseCase(UserInfoType.EMAIL),
            )
        }
    }

    fun onLogOutButtonClicked(
        navController: NavController,
    ) {
        viewModelScope.launch {
            val result = logOutUseCase()
            if (result is Result.SuccessWithoutBody) {
                navController.navigate(NavRoute.Login.route)
            } else {
                // TODO: show an error dialog.
            }
        }
    }

    fun onChangePasswordButtonClicked() {
        TODO("Not yet implemented")
    }
}
