package com.comp491.investsmart.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.comp491.investsmart.domain.comments.entities.Comment
import com.comp491.investsmart.domain.comments.entities.CommentAction
import com.comp491.investsmart.domain.comments.usecases.GetAssetCommentsUseCase
import com.comp491.investsmart.domain.comments.usecases.GetUserLikedCommentsUseCase
import com.comp491.investsmart.domain.comments.usecases.LikeUnlikeCommentUseCase
import com.comp491.investsmart.domain.users.entities.UserInfoType
import com.comp491.investsmart.domain.users.usecases.GetUserInfoUseCase
import com.comp491.investsmart.navigation.NavRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ProfileVMState(
    var comments: List<Comment>,
    var likedComments: List<Comment>,
    val username: String,
    val isLoading: Boolean,
)

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val getAssetCommentsUseCase: GetAssetCommentsUseCase,
    private val getUserLikedCommentsUseCase: GetUserLikedCommentsUseCase,
    private val likeUnlikeCommentUseCase: LikeUnlikeCommentUseCase,
) : ViewModel() {

    private val vmState = ProfileVMState(
        comments = emptyList(),
        likedComments = emptyList(),
        username = "",
        isLoading = true,
    )
    private val _vmState = MutableStateFlow(vmState)
    val uiState: StateFlow<ProfileVMState> = _vmState.asStateFlow()

    init {
        viewModelScope.launch {

            val comments = async {
                getAssetCommentsUseCase(
                    assetTicker = null,
                    commentParent = null,
                    userId = null, //TODO: User id here.
                ).data ?: emptyList()
            }

            val likedComments = async {
                getUserLikedCommentsUseCase(userId = null).data ?: emptyList()
            }

            val username = async {
                getUserInfoUseCase(infoType = UserInfoType.USERNAME)
            }

            _vmState.value = ProfileVMState(
                comments = comments.await(),
                likedComments = likedComments.await(),
                username = username.await(),
                isLoading = false,
            )
        }
    }

    fun onAnswerButtonClicked(commentId: Int, navController: NavController) {
        navController.navigate(NavRoute.Comments.withArgs(commentId.toString()))
    }

    fun onAssetTickerClicked(navController: NavController, assetTicker: String) {
        navController.navigate(NavRoute.AssetDetail.withArgs(assetTicker))
    }

    fun onCommentLikeButtonClicked(commentId: Int) {
        viewModelScope.launch {
            if(_vmState.value.likedComments.any { x -> x.id == commentId}){
                async{
                    likeUnlikeCommentUseCase.invoke(commentId = commentId, CommentAction.UNLIKE)
                }.await()
            }else{
                async{
                    likeUnlikeCommentUseCase.invoke(commentId = commentId, CommentAction.LIKE)
                }.await()
            }
            _vmState.value.comments = async {
                getAssetCommentsUseCase(
                    assetTicker = null,
                    commentParent = null,
                    userId = null, //TODO: User id here.
                ).data ?: emptyList()
            }.await()

            _vmState.value.likedComments = async {
                getUserLikedCommentsUseCase(
                    userId = null
                ).data ?: emptyList()
            }.await()

        }
    }
}
