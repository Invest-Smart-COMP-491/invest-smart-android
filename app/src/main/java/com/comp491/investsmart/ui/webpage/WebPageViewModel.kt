package com.comp491.investsmart.ui.webpage

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.comp491.investsmart.navigation.NavRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class WebPageVMState(
    var url: String,
    val isLoading: Boolean,
)

@HiltViewModel
class WebPageViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val vmState = WebPageVMState(
        url = "https://www.geeksforgeeks.org",
        isLoading = false,
    )
    private val _vmState = MutableStateFlow(vmState)
    val uiState: StateFlow<WebPageVMState> = _vmState.asStateFlow()

    init {
        val url = savedStateHandle.get<String>(NavRoute.WebPage.url).toString().replace(" ", "/")
        _vmState.value.url = url
    }
}
