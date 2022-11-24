package com.comp491.investsmart.data.comments.inject

import com.comp491.investsmart.data.comments.repositories.internal.CommentsRepositoryImpl
import com.comp491.investsmart.domain.comments.repositories.CommentsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class HiltCommentsModule {

    @Binds
    abstract fun bindCommentsRepository(
        commentsRepositoryImpl: CommentsRepositoryImpl
    ): CommentsRepository
}
