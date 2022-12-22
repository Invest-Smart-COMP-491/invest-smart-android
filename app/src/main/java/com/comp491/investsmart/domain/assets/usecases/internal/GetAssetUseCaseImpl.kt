package com.comp491.investsmart.domain.assets.usecases.internal

import com.comp491.investsmart.data.api.Result
import com.comp491.investsmart.domain.assets.entities.Asset
import com.comp491.investsmart.domain.assets.repositories.AssetsRepository
import com.comp491.investsmart.domain.assets.usecases.GetAssetUseCase
import javax.inject.Inject

class GetAssetUseCaseImpl @Inject constructor(
    private val assetsRepository: AssetsRepository,
): GetAssetUseCase {

    override suspend fun invoke(assetTicker: String): Result<Asset> {
        return assetsRepository.getAsset(assetTicker = assetTicker)
    }
}