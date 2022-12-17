package com.comp491.investsmart.ui.comments

import androidx.lifecycle.ViewModel
import com.comp491.investsmart.data.api.retrofit.InvestSmartService
import com.comp491.investsmart.domain.comments.entities.Comment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class CommentsVMState(
    val comments: List<Comment>,
)

@HiltViewModel
class CommentsViewModel @Inject constructor(
    private val investSmartService: InvestSmartService,
) : ViewModel() {

    private val vmState = CommentsVMState(
        comments = List(20) {
            Comment(
                id = 0,
                username = "golden",
                userId = 12,
                assetTicker = "PHID",
                text = "If you never walked at least once, it was a road ride.",
                date = "23.06.2021",
                likeCount = 10,
                importedFrom = "",
                answerCount = 5,
            )
        },
    )
    private val _vmState = MutableStateFlow(vmState)
    val uiState: StateFlow<CommentsVMState> = _vmState.asStateFlow()

    init {

    }

    fun onLikeButtonClicked(commentId: Int) {

    }

    fun onUsernameClicked(userId: Int) {

    }

    fun onReplyClicked(text: String) {

    }
}
