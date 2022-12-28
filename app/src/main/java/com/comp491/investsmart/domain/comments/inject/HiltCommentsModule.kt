package com.comp491.investsmart.domain.comments.inject

import com.comp491.investsmart.domain.comments.usecases.AddCommentUseCase
import com.comp491.investsmart.domain.comments.usecases.GetAssetCommentsUseCase
import com.comp491.investsmart.domain.comments.usecases.GetCommentByIdUseCase
import com.comp491.investsmart.domain.comments.usecases.LikeUnlikeCommentUseCase
import com.comp491.investsmart.domain.comments.usecases.internal.AddCommentUseCaseImpl
import com.comp491.investsmart.domain.comments.usecases.internal.GetAssetCommentsUseCaseImpl
import com.comp491.investsmart.domain.comments.usecases.internal.GetCommentByIdUseCaseImpl
import com.comp491.investsmart.domain.comments.usecases.internal.LikeUnlikeCommentUseCaseImpl
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
