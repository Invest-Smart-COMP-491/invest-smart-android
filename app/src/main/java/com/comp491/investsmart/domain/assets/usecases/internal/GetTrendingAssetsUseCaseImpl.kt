package com.comp491.investsmart.domain.assets.usecases.internal

import com.comp491.investsmart.domain.assets.entities.Asset
import com.comp491.investsmart.domain.assets.repositories.AssetsRepository
import com.comp491.investsmart.domain.assets.usecases.GetTrendingAssetsUseCase
import javax.inject.Inject

class GetTrendingAssetsUseCaseImpl @Inject constructor(
    private val assetsRepository: AssetsRepository
): GetTrendingAssetsUseCase {

    override suspend fun invoke(): List<Asset> {
        return assetsRepository.getTrendingAssets()
    }
}
