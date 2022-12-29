package com.comp491.investsmart.domain.comments.inject

import com.comp491.investsmart.domain.comments.usecases.*
import com.comp491.investsmart.domain.comments.usecases.internal.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class HiltCommentsModule {

    @Binds
    abstract fun bindGetAssetCommentsUseCase(
        getAssetCommentsUseCaseImpl: GetAssetCommentsUseCaseImpl
    ): GetAssetCommentsUseCase

    @Binds
    abstract fun bindGetUserLikedCommentsUseCase(
        getUserLikedCommentsUseCaseImpl: GetUserLikedCommentsUseCaseImpl
    ): GetUserLikedCommentsUseCase

    @Binds
    abstract fun bindAddCommentUseCase(
        addCommentUseCaseImpl: AddCommentUseCaseImpl
    ): AddCommentUseCase

    @Binds
    abstract fun bindGetCommentByIdUseCase(
        getCommentByIdUseCaseImpl: GetCommentByIdUseCaseImpl
    ): GetCommentByIdUseCase

    @Binds
    abstract fun bindLikeUnlikeCommentUseCase(
        likeUnlikeCommentUseCaseImpl: LikeUnlikeCommentUseCaseImpl
    ): LikeUnlikeCommentUseCase
}
