package com.comp491.investsmart.domain.assets.usecases.internal

import com.comp491.investsmart.data.api.Result
import com.comp491.investsmart.domain.assets.entities.Asset
import com.comp491.investsmart.domain.assets.repositories.AssetsRepository
import com.comp491.investsmart.domain.assets.usecases.GetFavouriteAssetsUseCase
import javax.inject.Inject

class GetFavouriteAssetsUseCaseImpl @Inject constructor(
    private val assetsRepository: AssetsRepository
): GetFavouriteAssetsUseCase {

    override suspend fun invoke(): Result<List<Asset>> {
        return assetsRepository.getFavouriteAssets()
    }
}
