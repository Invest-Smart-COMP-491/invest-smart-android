package com.comp491.investsmart.data.assets.inject

import com.comp491.investsmart.data.assets.repositories.internal.AssetsRepositoryImpl
import com.comp491.investsmart.domain.assets.repositories.AssetsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class HiltAssetsModule {

    @Binds
    abstract fun bindAssetsRepository(
        assetsRepositoryImpl: AssetsRepositoryImpl
    ): AssetsRepository
}
