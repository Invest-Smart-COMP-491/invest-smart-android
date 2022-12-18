package com.comp491.investsmart.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.comp491.investsmart.domain.assets.entities.Asset
import com.comp491.investsmart.domain.assets.usecases.GetAssetsWithKeywordUseCase
import com.comp491.investsmart.navigation.NavRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SearchVMState(
    val assets: List<Asset>,
    var isLoading: Boolean,
)

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getAssetsWithKeywordUseCase: GetAssetsWithKeywordUseCase
) : ViewModel() {

    private val vmState = SearchVMState(
        assets = emptyList(),
        isLoading = false,
    )
    private val _vmState = MutableStateFlow(vmState)
    val uiState: StateFlow<SearchVMState> = _vmState.asStateFlow()

    init {}

    fun onSearchRequested(text: String) {
        _vmState.value.isLoading = true
        viewModelScope.launch {
            try {
                val searchAssets = async {
                    getAssetsWithKeywordUseCase(text).data ?: emptyList()
                }
                _vmState.value = SearchVMState(
                    assets = searchAssets.await(),
                    isLoading = false,
                )
            } catch (e: java.lang.Exception) {
                _vmState.value = SearchVMState(
                    assets = emptyList(),
                    isLoading = true,
                )
                e.printStackTrace();
            }
        }
    }

    fun onAssetClicked(
        assetTicker: String,
        navController: NavController,
    ) {
        navController.navigate(NavRoute.AssetDetail.withArgs(assetTicker))
    }
}
