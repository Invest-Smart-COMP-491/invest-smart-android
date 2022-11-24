package com.comp491.investsmart.domain.assets.inject

import com.comp491.investsmart.domain.assets.usecases.GetAllAssetPricesUseCase
import com.comp491.investsmart.domain.assets.usecases.GetAllAssetsUseCase
import com.comp491.investsmart.domain.assets.usecases.GetAllFavouriteAssetsUseCase
import com.comp491.investsmart.domain.assets.usecases.GetAssetPricesUseCase
import com.comp491.investsmart.domain.assets.usecases.internal.GetAllAssetPricesUseCaseImpl
import com.comp491.investsmart.domain.assets.usecases.internal.GetAllAssetsUseCaseImpl
import com.comp491.investsmart.domain.assets.usecases.internal.GetAllFavouriteAssetsUseCaseImpl
import com.comp491.investsmart.domain.assets.usecases.internal.GetAssetPricesUseCaseImpl
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
    abstract fun bindGetAllAssetPricesUseCase(
        getAllAssetPricesUseCaseImpl: GetAllAssetPricesUseCaseImpl
    ): GetAllAssetPricesUseCase

    @Binds
    abstract fun bindGetAllFavouriteAssetsUseCase(
        getAllFavouriteAssetsUseCaseImpl: GetAllFavouriteAssetsUseCaseImpl
    ): GetAllFavouriteAssetsUseCase

    @Binds
    abstract fun bindGetAllAssetsUseCase(
        getAllAssetsUseCaseImpl: GetAllAssetsUseCaseImpl
    ): GetAllAssetsUseCase
}
