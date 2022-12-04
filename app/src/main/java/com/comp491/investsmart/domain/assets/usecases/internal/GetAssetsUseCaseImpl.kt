package com.comp491.investsmart.domain.assets.usecases.internal

import com.comp491.investsmart.domain.assets.entities.Asset
import com.comp491.investsmart.domain.assets.repositories.AssetsRepository
import com.comp491.investsmart.domain.assets.usecases.GetAssetsUseCase
import javax.inject.Inject

class GetAssetsUseCaseImpl @Inject constructor(
    private val assetsRepository: AssetsRepository
): GetAssetsUseCase {

    override suspend fun invoke(): List<Asset> {
        return assetsRepository.getAssets()
    }
}
