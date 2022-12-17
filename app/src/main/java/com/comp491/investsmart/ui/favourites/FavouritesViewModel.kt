package com.comp491.investsmart.ui.favourites

import androidx.lifecycle.ViewModel
import com.comp491.investsmart.domain.assets.entities.Asset
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class FavouritesVMState(
    val assets: List<Asset>,
)

@HiltViewModel
class FavouritesViewModel @Inject constructor(
) : ViewModel() {

    private val vmState = FavouritesVMState(
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
    val uiState: StateFlow<FavouritesVMState> = _vmState.asStateFlow()
}