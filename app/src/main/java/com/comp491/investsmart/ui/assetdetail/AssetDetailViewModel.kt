package com.comp491.investsmart.ui.assetdetail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.comp491.investsmart.domain.assets.entities.Asset
import com.comp491.investsmart.navigation.NavRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class AssetDetailVMState(
    val asset: Asset,
)

@HiltViewModel
class AssetDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val vmState = AssetDetailVMState(
        asset = Asset(
            assetName = "META",
            assetTicker = "FB",
            lastPrice = 3.14,
            assetCategory = "Tech",
            viewCount = 12,
            photoUrl = "https://picsum.photos/200",
            marketSize = 200.0,
        )
    )
    private val _vmState = MutableStateFlow(vmState)
    val uiState: StateFlow<AssetDetailVMState> = _vmState.asStateFlow()

    init {
        Log.d("damla", savedStateHandle.get<String>(NavRoute.AssetDetail.assetTicker).toString())
    }
}
