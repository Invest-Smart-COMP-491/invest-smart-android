package com.comp491.investsmart.domain.assets.usecases

import com.comp491.investsmart.domain.assets.entities.FavouriteAssetAction

interface FollowUnfollowAssetUseCase {

    suspend operator fun invoke(favouriteAssetAction: FavouriteAssetAction, assetTicker: String)
}
