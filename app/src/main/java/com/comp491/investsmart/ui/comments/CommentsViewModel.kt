package com.comp491.investsmart.ui.comments

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comp491.investsmart.domain.comments.entities.Comment
import com.comp491.investsmart.domain.comments.usecases.AddCommentUseCase
import com.comp491.investsmart.domain.comments.usecases.GetAssetCommentsUseCase
import com.comp491.investsmart.navigation.NavRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CommentsVMState(
    val comments: List<Comment>,
    val isLoading: Boolean,
)

@HiltViewModel
class CommentsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getAssetCommentsUseCase: GetAssetCommentsUseCase,
    private val addCommentUseCase: AddCommentUseCase,
) : ViewModel() {

    private val vmState = CommentsVMState(
        comments = emptyList(),
        isLoading = true,
    )
    private val _vmState = MutableStateFlow(vmState)
    val uiState: StateFlow<CommentsVMState> = _vmState.asStateFlow()
    val commentId = savedStateHandle.get<String>(NavRoute.Comments.commentId)?.toString()

    init {
        commentId?.let {
            viewModelScope.launch {
                // TODO: Handle error
                val comments: List<Comment> =
                    getAssetCommentsUseCase(
                        assetTicker = null,
                        commentParent = commentId,
                        userId = null,
                    ).data ?: emptyList()

                _vmState.value = CommentsVMState(
                    comments = comments,
                    isLoading = false,
                )
            }
        }
    }

    fun onLikeButtonClicked(commentId: Int) {

    }

    fun onUsernameClicked(userId: Int) {

    }

    fun onReplyClicked(text: String) {
        viewModelScope.launch {
            addCommentUseCase(
                assetTicker = vmState.comments[0].assetTicker,
                text = text,
                parentId = commentId?.toInt(),
            )
        }
    }
}
