package com.comp491.investsmart.ui.settings

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class SettingsVMState(
    val username: String,
    val email: String,
)

@HiltViewModel
class SettingsViewModel  @Inject constructor(
) : ViewModel() {

    private val vmState = SettingsVMState(
        username = "",
        email = ""
    )
    private val _vmState = MutableStateFlow(vmState)
    val uiState: StateFlow<SettingsVMState> = _vmState.asStateFlow()

    init {
        _vmState.value = SettingsVMState(
            username = "",
            email = ""
        )
    }

    fun onLogOutButtonClicked() {
        TODO("Not yet implemented")
    }

    fun onChangePasswordButtonClicked() {
        TODO("Not yet implemented")
    }
}
