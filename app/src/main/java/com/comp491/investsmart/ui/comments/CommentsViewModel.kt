package com.comp491.investsmart.ui.comments

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comp491.investsmart.domain.comments.entities.Comment
import com.comp491.investsmart.domain.comments.entities.CommentAction
import com.comp491.investsmart.domain.comments.usecases.*
import com.comp491.investsmart.navigation.NavRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CommentsVMState(
    var comments: List<Comment>,
    var likedComments : List<Comment>,
    val isLoading: Boolean,
    var parentComment : Comment,
)

@HiltViewModel
class CommentsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getAssetCommentsUseCase: GetAssetCommentsUseCase,
    private val addCommentUseCase: AddCommentUseCase,
    private val getUserLikedCommentsUseCase: GetUserLikedCommentsUseCase,
    private val likeUnlikeCommentUseCase: LikeUnlikeCommentUseCase,
    private val getCommentByIdUseCase: GetCommentByIdUseCase,
) : ViewModel() {

    private val vmState = CommentsVMState(
        comments = emptyList(),
        likedComments = emptyList(),
        isLoading = true,
        parentComment = Comment(-1,"",-1,"","","",0,"",0),
    )
    private val _vmState = MutableStateFlow(vmState)
    val uiState: StateFlow<CommentsVMState> = _vmState.asStateFlow()
    val commentId = savedStateHandle.get<String>(NavRoute.Comments.commentId)?.toString()

    init {
        refreshComments()
    }

    fun onLikeButtonClicked(commentId: Int) {
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
                    commentParent = _vmState.value.parentComment.id.toString(),
                    userId = null,
                ).data ?: emptyList()
            }.await()

            _vmState.value.likedComments = async {
                getUserLikedCommentsUseCase(
                    userId = null
                ).data ?: emptyList()
            }.await()

        }
    }

    fun onReplyClicked(text: String) {
        viewModelScope.launch {
            addCommentUseCase(
                assetTicker = _vmState.value.parentComment.assetTicker,
                text = text,
                parentId = commentId?.toInt(),
            )

            refreshComments()
        }
    }

    private fun refreshComments() {
        commentId?.let {
            viewModelScope.launch {
                // TODO: Handle error
                val parentComment: Comment =
                    getCommentByIdUseCase(commentId = commentId.toInt()).data ?:
                    vmState.parentComment

                val comments: List<Comment> =
                    getAssetCommentsUseCase(
                        assetTicker = null,
                        commentParent = commentId,
                        userId = null,
                    ).data ?: emptyList()

                val likedComments: List<Comment> =
                    getUserLikedCommentsUseCase(userId = null).data ?: emptyList()

                _vmState.value = CommentsVMState(
                    comments = comments,
                    likedComments = likedComments,
                    parentComment = parentComment,
                    isLoading = false,
                )
            }
        }
    }
}
