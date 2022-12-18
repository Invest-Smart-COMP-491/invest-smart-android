package com.comp491.investsmart.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.comp491.investsmart.domain.assets.entities.Asset
import com.comp491.investsmart.domain.assets.usecases.GetTrendingAssetsUseCase
import com.comp491.investsmart.domain.news.entities.News
import com.comp491.investsmart.domain.news.usecases.GetAllNewsUseCase
import com.comp491.investsmart.navigation.NavRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeVMState(
    val trendingAssets: List<Asset>,
    val news: List<News>,
    val isLoading: Boolean,
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllNewsUseCase: GetAllNewsUseCase,
    private val getTrendingAssetsUseCase: GetTrendingAssetsUseCase,
) : ViewModel() {

    private val vmState = HomeVMState(
        trendingAssets = emptyList(),
        news = emptyList(),
        isLoading = true,
    )
    private val _vmState = MutableStateFlow(vmState)
    val uiState: StateFlow<HomeVMState> = _vmState.asStateFlow()

    init {
        viewModelScope.launch {
            // TODO: handle error case, show an error dialog
            val news = async {
                getAllNewsUseCase().data ?: emptyList()
            }

            // TODO: handle error case, show an error dialog
            val trendingAssets = async {
                getTrendingAssetsUseCase().data ?: emptyList()
            }

            _vmState.value = HomeVMState(
                news = news.await(),
                trendingAssets = trendingAssets.await(),
                isLoading = false,
            )
        }
    }

    fun onNewsClicked(
        url: String,
        navController: NavController,
    ) {
        navController.navigate(NavRoute.WebPage.withArgs(url.replace("/", " ")))
    }

    fun onAssetClicked(
        assetTicker: String,
        navController: NavController,
    ) {
        navController.navigate(NavRoute.AssetDetail.withArgs(assetTicker))
    }
}
