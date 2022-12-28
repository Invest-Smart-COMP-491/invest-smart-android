package com.comp491.investsmart.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.comp491.investsmart.domain.comments.entities.Comment
import com.comp491.investsmart.domain.comments.usecases.GetAssetCommentsUseCase
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
    val comments: List<Comment>,
    val username: String,
    val isLoading: Boolean,
)

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val getAssetCommentsUseCase: GetAssetCommentsUseCase,
) : ViewModel() {

    private val vmState = ProfileVMState(
        comments = emptyList(),
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

            val username = async {
                getUserInfoUseCase(infoType = UserInfoType.USERNAME)
            }

            _vmState.value = ProfileVMState(
                comments = comments.await(),
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
}
