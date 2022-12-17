package com.comp491.investsmart.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comp491.investsmart.domain.users.entities.UserInfoType
import com.comp491.investsmart.domain.users.usecases.GetUserInfoUseCase
import com.comp491.investsmart.domain.users.usecases.LogOutUseCase
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

    fun onLogOutButtonClicked() {
        viewModelScope.launch {
            logOutUseCase()
        }
    }

    fun onChangePasswordButtonClicked() {
        TODO("Not yet implemented")
    }
}
