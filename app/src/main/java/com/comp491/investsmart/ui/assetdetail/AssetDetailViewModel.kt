package com.comp491.investsmart.ui.assetdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.comp491.investsmart.data.api.Constant
import com.comp491.investsmart.domain.assets.entities.Asset
import com.comp491.investsmart.domain.assets.usecases.GetAssetUseCase
import com.comp491.investsmart.domain.comments.entities.Comment
import com.comp491.investsmart.domain.comments.usecases.GetAssetCommentsUseCase
import com.comp491.investsmart.domain.news.entities.News
import com.comp491.investsmart.domain.news.usecases.GetAssetNewsUseCase
import com.comp491.investsmart.navigation.NavRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class PageType {
    COMMENTS,
    NEWS,
}

data class AssetDetailVMState(
    var asset: Asset,
    val assetNews: List<News>,
    val assetComments: List<Comment>,
    val isLoading: Boolean,
    val pageType: PageType,
)

@HiltViewModel
class AssetDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getAssetUseCase: GetAssetUseCase,
    private val getAssetNewsUseCase: GetAssetNewsUseCase,
    private val getAssetCommentsUseCase: GetAssetCommentsUseCase,
) : ViewModel() {

    private val vmState = AssetDetailVMState(
        asset = Asset(
            assetName = "Apple",
            assetTicker = "AAPL",
            lastPrice = 3.14,
            assetCategory = "Tech",
            viewCount = 12,
            photoUrl = "https://picsum.photos/200",
            marketSize = 200.0,
        ),
        assetNews = emptyList(),
        assetComments = emptyList(),
        isLoading = true,
        pageType = PageType.NEWS,
    )
    private val _vmState = MutableStateFlow(vmState)
    val uiState: StateFlow<AssetDetailVMState> = _vmState.asStateFlow()

    init {
        val ticker = savedStateHandle.get<String>(NavRoute.AssetDetail.assetTicker).toString()
        viewModelScope.launch {
            val asset = async {
                getAssetUseCase(ticker).data ?: _vmState.value.asset
            }

            val news = async {
                getAssetNewsUseCase(assetTicker = ticker).data ?: emptyList()
            }

            val comments = async {
                getAssetCommentsUseCase(assetTicker = ticker, commentParent = "", userId = null).data ?: emptyList()
            }

            _vmState.value = AssetDetailVMState(
                asset = asset.await(),
                assetNews = news.await(),
                assetComments = comments.await(),
                isLoading = false,
                pageType = PageType.NEWS,
            )
        }
    }

    fun onPageTypeChanged(pageType: PageType) {
        _vmState.value = AssetDetailVMState(
            asset = vmState.asset,
            assetComments = vmState.assetComments,
            assetNews = vmState.assetNews,
            isLoading = false,
            pageType = pageType,
        )
    }

    fun onPriceGraphButtonClicked(navController: NavController) {
        val url = Constant.baseUrl + "plot/"+ vmState.asset.assetTicker
        navController.navigate(NavRoute.WebPage.withArgs(url.replace("/", " ")))
    }

    fun onNewsClicked(url: String, navController: NavController) {
        navController.navigate(NavRoute.WebPage.withArgs(url.replace("/", " ")))
    }

    fun onAnswerButtonClicked(commentId: Int, navController: NavController) {
        navController.navigate(NavRoute.Comments.withArgs(commentId.toString()))
    }

    fun onCommentLikeButtonClicked(commentId: Int) {

    }

    fun onAssetFavouriteButtonClicked() {

    }
}
