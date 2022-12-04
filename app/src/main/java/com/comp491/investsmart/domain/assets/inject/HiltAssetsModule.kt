package com.comp491.investsmart.domain.assets.inject

import com.comp491.investsmart.domain.assets.usecases.*
import com.comp491.investsmart.domain.assets.usecases.internal.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class HiltAssetsModule {

    @Binds
    abstract fun bindGetAssetPricesUseCase(
        getAssetPricesUseCaseImpl: GetAssetPricesUseCaseImpl
    ): GetAssetPricesUseCase

    @Binds
    abstract fun bindGetFavouriteAssetsUseCase(
        getAllFavouriteAssetsUseCaseImpl: GetAllFavouriteAssetsUseCaseImpl
    ): GetFavouriteAssetsUseCase

    @Binds
    abstract fun bindGetAssetsUseCase(
        getAssetsUseCaseImpl: GetAssetsUseCaseImpl
    ): GetAssetsUseCase

    @Binds
    abstract fun bindGetAssetsWithKeywordUseCase(
        getAssetsWithKeywordUseCaseImpl: GetAssetsWithKeywordUseCaseImpl
    ): GetAssetsWithKeywordUseCase
}
