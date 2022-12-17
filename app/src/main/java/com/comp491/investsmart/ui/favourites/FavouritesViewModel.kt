package com.comp491.investsmart.ui.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.comp491.investsmart.domain.assets.entities.Asset
import com.comp491.investsmart.domain.assets.usecases.GetFavouriteAssetsUseCase
import com.comp491.investsmart.navigation.NavRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class FavouritesVMState(
    val assets: List<Asset>,
    val isLoading: Boolean,
)

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val getFavouriteAssetsUseCase: GetFavouriteAssetsUseCase,
) : ViewModel() {

    private val vmState = FavouritesVMState(
        assets = emptyList(),
        isLoading = true,
    )
    private val _vmState = MutableStateFlow(vmState)
    val uiState: StateFlow<FavouritesVMState> = _vmState.asStateFlow()

    init {
        viewModelScope.launch {
            // TODO: Show an error dialog in case of an error.
            _vmState.value = FavouritesVMState(
                assets = getFavouriteAssetsUseCase().data ?: emptyList(),
                isLoading = false,
            )
        }
    }

    fun onAssetClicked(
        assetTicker: String,
        navController: NavController,
    ) {
        navController.navigate(NavRoute.AssetDetail.withArgs(assetTicker))
    }
}