package com.comp491.investsmart.ui.search

import androidx.lifecycle.ViewModel
import com.comp491.investsmart.domain.assets.entities.Asset
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class SearchVMState(
    val assets: List<Asset>,
)

@HiltViewModel
class SearchViewModel @Inject constructor(
) : ViewModel() {

    private val vmState = SearchVMState(
        assets = emptyList()
    )
    private val _vmState = MutableStateFlow(vmState)
    val uiState: StateFlow<SearchVMState> = _vmState.asStateFlow()

    fun onSearchRequested(text: String) {
        TODO("Not yet implemented")
    }
}
