package com.comp491.investsmart.domain.assets.usecases.internal

import com.comp491.investsmart.domain.assets.entities.FavouriteAssetAction
import com.comp491.investsmart.domain.assets.repositories.AssetsRepository
import com.comp491.investsmart.domain.assets.usecases.FollowUnfollowAssetUseCase
import javax.inject.Inject

class FollowUnfollowAssetUseCaseImpl @Inject constructor(
    private val assetsRepository: AssetsRepository
): FollowUnfollowAssetUseCase {

    override suspend fun invoke(favouriteAssetAction: FavouriteAssetAction, assetTicker: String) {
        when (favouriteAssetAction) {
            FavouriteAssetAction.FOLLOW -> {
                assetsRepository.followAsset(assetTicker = assetTicker)
            }
            FavouriteAssetAction.UNFOLLOW -> {
                assetsRepository.unfollowAsset(assetTicker = assetTicker)
            }
        }
    }
}
