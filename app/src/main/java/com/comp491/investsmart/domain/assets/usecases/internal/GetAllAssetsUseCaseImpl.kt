package com.comp491.investsmart.domain.assets.usecases.internal

import com.comp491.investsmart.domain.assets.entities.Asset
import com.comp491.investsmart.domain.assets.repositories.AssetsRepository
import com.comp491.investsmart.domain.assets.usecases.GetAllAssetsUseCase
import javax.inject.Inject

class GetAllAssetsUseCaseImpl @Inject constructor(
    private val assetsRepository: AssetsRepository
): GetAllAssetsUseCase {

    override suspend fun invoke(): List<Asset> {
        return assetsRepository.getAllAssets()
    }
}
