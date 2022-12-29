package com.comp491.investsmart.ui.assetdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.comp491.investsmart.data.api.Constant
import com.comp491.investsmart.domain.assets.entities.Asset
import com.comp491.investsmart.domain.assets.entities.FavouriteAssetAction
import com.comp491.investsmart.domain.assets.usecases.FollowUnfollowAssetUseCase
import com.comp491.investsmart.domain.assets.usecases.GetAssetUseCase
import com.comp491.investsmart.domain.assets.usecases.GetFavouriteAssetsUseCase
import com.comp491.investsmart.domain.comments.entities.Comment
import com.comp491.investsmart.domain.comments.entities.CommentAction
import com.comp491.investsmart.domain.comments.usecases.AddCommentUseCase
import com.comp491.investsmart.domain.comments.usecases.GetAssetCommentsUseCase
import com.comp491.investsmart.domain.comments.usecases.GetUserLikedCommentsUseCase
import com.comp491.investsmart.domain.comments.usecases.LikeUnlikeCommentUseCase
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
    var assetComments: List<Comment>,
    var likedComments: List<Comment>,
    var isFollowed: Boolean,
    val isLoading: Boolean,
    val pageType: PageType,
)

@HiltViewModel
class AssetDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getAssetUseCase: GetAssetUseCase,
    private val getAssetNewsUseCase: GetAssetNewsUseCase,
    private val getAssetCommentsUseCase: GetAssetCommentsUseCase,
    private val getFavouriteAssetsUseCase: GetFavouriteAssetsUseCase,
    private val followUnfollowAssetUseCase: FollowUnfollowAssetUseCase,
    private val addCommentUseCase: AddCommentUseCase,
    private val getUserLikedCommentsUseCase: GetUserLikedCommentsUseCase,
    private val likeUnlikeCommentUseCase: LikeUnlikeCommentUseCase,
) : ViewModel() {

    private val vmState = AssetDetailVMState(
        asset = Asset(
            assetName = "",
            assetTicker = "",
            lastPrice = 0.0,
            assetCategory = "",
            viewCount = 0,
            photoUrl = "",
            marketSize = 0.0,
        ),
        assetNews = emptyList(),
        assetComments = emptyList(),
        likedComments = emptyList(),
        isFollowed = false,
        isLoading = true,
        pageType = PageType.NEWS,
    )
    private val _vmState = MutableStateFlow(vmState)
    val uiState: StateFlow<AssetDetailVMState> = _vmState.asStateFlow()

    private val ticker = savedStateHandle.get<String>(NavRoute.AssetDetail.assetTicker).toString()

    init {
        viewModelScope.launch {
            val asset = async {
                getAssetUseCase(assetTicker = ticker).data ?: _vmState.value.asset
            }

            val news = async {
                getAssetNewsUseCase(assetTicker = ticker).data ?: emptyList()
            }

            val userFavouriteAssets = async{
                getFavouriteAssetsUseCase().data ?: emptyList()
            }

            _vmState.value = AssetDetailVMState(
                asset = asset.await(),
                assetNews = news.await(),
                assetComments = _vmState.value.assetComments,
                likedComments = _vmState.value.likedComments,
                isFollowed = userFavouriteAssets.await()
                    .any { x -> x.assetTicker == asset.await().assetTicker},
                isLoading = false,
                pageType = PageType.NEWS,
            )

            updateComments()
        }
    }

    fun onPageTypeChanged(pageType: PageType) {
        _vmState.value = AssetDetailVMState(
            asset = _vmState.value.asset,
            assetComments = _vmState.value.assetComments,
            likedComments = _vmState.value.likedComments,
            assetNews = _vmState.value.assetNews,
            isFollowed = _vmState.value.isFollowed,
            isLoading = false,
            pageType = pageType,
        )
    }

    fun onPriceGraphButtonClicked(navController: NavController) {
        val url = Constant.baseUrl + "plot/"+ _vmState.value.asset.assetTicker
        navController.navigate(NavRoute.WebPage.withArgs(url.replace("/", " ")))
    }

    fun onNewsClicked(url: String, navController: NavController) {
        navController.navigate(NavRoute.WebPage.withArgs(url.replace("/", " ")))
    }

    fun onAnswerButtonClicked(commentId: Int, navController: NavController) {
        navController.navigate(NavRoute.Comments.withArgs(commentId.toString()))
    }

    fun onCommentLikeButtonClicked(commentId: Int) {
        viewModelScope.launch {
            if(_vmState.value.likedComments.any { x -> x.id == commentId}){
                likeUnlikeCommentUseCase.invoke(commentId = commentId, CommentAction.UNLIKE)
            }else{
                likeUnlikeCommentUseCase.invoke(commentId = commentId, CommentAction.LIKE)
            }

            updateComments()
        }
    }

    fun onAssetFavouriteButtonClicked() {
        viewModelScope.launch {
            if(_vmState.value.isFollowed){
                followUnfollowAssetUseCase.invoke(
                    favouriteAssetAction = FavouriteAssetAction.UNFOLLOW,
                    assetTicker = _vmState.value.asset.assetTicker
                )
                _vmState.value.isFollowed = false
            }else{
                followUnfollowAssetUseCase.invoke(
                    favouriteAssetAction = FavouriteAssetAction.FOLLOW,
                    assetTicker = _vmState.value.asset.assetTicker
                )
                _vmState.value.isFollowed = true
            }
        }
    }

    fun onSendClicked(text: String) {
        viewModelScope.launch {
            addCommentUseCase(
                assetTicker = _vmState.value.asset.assetTicker,
                text = text,
                parentId = null,
            )

            updateComments()
        }
    }

    private fun updateComments() {
        viewModelScope.launch {
            val comments = async {
                getAssetCommentsUseCase(
                    assetTicker = ticker, commentParent = "", userId = null
                ).data ?: emptyList()
            }

            val likedComments = async {
                getUserLikedCommentsUseCase(userId = null).data ?: emptyList()
            }

            _vmState.value = AssetDetailVMState(
                asset = _vmState.value.asset,
                assetNews = _vmState.value.assetNews,
                assetComments = comments.await(),
                likedComments = likedComments.await(),
                isFollowed = _vmState.value.isFollowed,
                isLoading = _vmState.value.isLoading,
                pageType = _vmState.value.pageType,
            )
        }
    }
}
