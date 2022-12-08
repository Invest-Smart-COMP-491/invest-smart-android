package com.comp491.investsmart.domain.comments.inject

import com.comp491.investsmart.domain.comments.usecases.GetAssetCommentsUseCase
import com.comp491.investsmart.domain.comments.usecases.internal.GetAssetCommentsUseCaseImpl
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
}
