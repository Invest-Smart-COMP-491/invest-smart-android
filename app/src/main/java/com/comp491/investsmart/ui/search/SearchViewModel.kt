package com.comp491.investsmart.ui.search

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.comp491.investsmart.domain.assets.entities.Asset
import com.comp491.investsmart.navigation.NavRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class SearchVMState(
    val assets: List<Asset>,
    val isLoading: Boolean,
)

@HiltViewModel
class SearchViewModel @Inject constructor(
) : ViewModel() {

    private val vmState = SearchVMState(
        assets = emptyList(),
        isLoading = false,
    )
    private val _vmState = MutableStateFlow(vmState)
    val uiState: StateFlow<SearchVMState> = _vmState.asStateFlow()

    init {}

    fun onSearchRequested(text: String) {
        TODO("Not yet implemented, don't forget isLoading")
    }

    fun onAssetClicked(
        assetTicker: String,
        navController: NavController,
    ) {
        navController.navigate(NavRoute.AssetDetail.withArgs(assetTicker))
    }
}
