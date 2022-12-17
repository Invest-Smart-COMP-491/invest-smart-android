package com.comp491.investsmart.ui.profile

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.comp491.investsmart.data.api.retrofit.InvestSmartService
import com.comp491.investsmart.domain.comments.entities.Comment
import com.comp491.investsmart.navigation.NavRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class ProfileVMState(
    val comments: List<Comment>,
    val username: String,
)

@HiltViewModel
class ProfileViewModel @Inject constructor(
) : ViewModel() {

    private val vmState = ProfileVMState(
        comments = emptyList(),
        username = "",
    )
    private val _vmState = MutableStateFlow(vmState)
    val uiState: StateFlow<ProfileVMState> = _vmState.asStateFlow()

    init {
        _vmState.value = ProfileVMState(
            comments = List(20) {
                Comment(
                    id = 0,
                    username = "cinnamon",
                    userId = 12,
                    assetTicker = "DAMY",
                    text = "Ah, a functional programmer. Inheritance is messy anyway.",
                    date = "23.06.2021",
                    likeCount = 10,
                    importedFrom = "",
                    answerCount = 5,
                )
            },
            username = "cinnamon",
        )
    }

    fun onAnswerButtonClicked(commentId: Int, navController: NavController) {
        navController.navigate(NavRoute.Comments.withArgs(commentId.toString()))
    }

    fun onLikeButtonClicked(commentId: Int) {

    }

    fun onAssetTickerClicked(assetTicker: String) {

    }
}
