package com.comp491.investsmart.domain.assets.usecases.internal

import com.comp491.investsmart.domain.assets.entities.AssetPrice
import com.comp491.investsmart.domain.assets.repositories.AssetsRepository
import com.comp491.investsmart.domain.assets.usecases.GetAllAssetPricesUseCase
import javax.inject.Inject

class GetAllAssetPricesUseCaseImpl @Inject constructor(
    private val assetsRepository: AssetsRepository,
): GetAllAssetPricesUseCase {

    override suspend fun invoke(): List<AssetPrice> {
        return assetsRepository.getAllAssetPrices()
    }
}
