package com.comp491.investsmart.domain.news.inject

import com.comp491.investsmart.domain.news.usecases.GetAllNewsUseCase
import com.comp491.investsmart.domain.news.usecases.GetAssetNewsUseCase
import com.comp491.investsmart.domain.news.usecases.internal.GetAllNewsUseCaseImpl
import com.comp491.investsmart.domain.news.usecases.internal.GetAssetNewsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class HiltNewsModule {

    @Binds
    abstract fun bindGetAllNewsUseCase(
        getAllNewsUseCaseImpl: GetAllNewsUseCaseImpl
    ): GetAllNewsUseCase

    @Binds
    abstract fun bindGetAssetNewsUseCase(
        getAssetNewsUseCaseImpl: GetAssetNewsUseCaseImpl
    ): GetAssetNewsUseCase
}
