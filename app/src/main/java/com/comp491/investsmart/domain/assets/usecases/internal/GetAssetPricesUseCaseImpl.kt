package com.comp491.investsmart.domain.assets.usecases.internal

import com.comp491.investsmart.data.api.Result
import com.comp491.investsmart.domain.assets.entities.AssetPrice
import com.comp491.investsmart.domain.assets.repositories.AssetsRepository
import com.comp491.investsmart.domain.assets.usecases.GetAssetPricesUseCase
import javax.inject.Inject

class GetAssetPricesUseCaseImpl @Inject constructor(
    private val assetsRepository: AssetsRepository
): GetAssetPricesUseCase {

    override suspend fun invoke(assetTicker: String): Result<List<AssetPrice>> {
        return assetsRepository.getAssetPrices(assetTicker = assetTicker)
    }
}
