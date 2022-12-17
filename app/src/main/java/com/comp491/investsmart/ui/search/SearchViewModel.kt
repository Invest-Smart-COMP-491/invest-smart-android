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
)

@HiltViewModel
class SearchViewModel @Inject constructor(
) : ViewModel() {

    private val vmState = SearchVMState(
        assets = List(20) {
            Asset(
                assetName = "META",
                assetTicker = "FB",
                lastPrice = 3.14,
                assetCategory = "Tech",
                viewCount = 12,
                photoUrl = "https://picsum.photos/200",
                marketSize = 200.0,
            )
        },
    )
    private val _vmState = MutableStateFlow(vmState)
    val uiState: StateFlow<SearchVMState> = _vmState.asStateFlow()

    fun onSearchRequested(text: String) {
        TODO("Not yet implemented")
    }

    fun onAssetClicked(
        assetTicker: String,
        navController: NavController,
    ) {
        navController.navigate(NavRoute.AssetDetail.route)
    }
}
