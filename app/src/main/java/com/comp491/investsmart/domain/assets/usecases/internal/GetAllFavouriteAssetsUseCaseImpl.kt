package com.comp491.investsmart.domain.assets.usecases.internal

import com.comp491.investsmart.domain.assets.entities.Asset
import com.comp491.investsmart.domain.assets.repositories.AssetsRepository
import com.comp491.investsmart.domain.assets.usecases.GetAllFavouriteAssetsUseCase
import javax.inject.Inject

class GetAllFavouriteAssetsUseCaseImpl @Inject constructor(
    private val assetsRepository: AssetsRepository
): GetAllFavouriteAssetsUseCase {

    override suspend fun invoke(): List<Asset> {
        return assetsRepository.getAllFavouriteAssets()
    }
}
