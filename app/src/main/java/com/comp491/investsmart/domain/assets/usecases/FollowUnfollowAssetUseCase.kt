package com.comp491.investsmart.domain.assets.usecases

import com.comp491.investsmart.domain.assets.entities.FavouriteAssetAction
import com.comp491.investsmart.data.api.Result

interface FollowUnfollowAssetUseCase {

    suspend operator fun invoke(favouriteAssetAction: FavouriteAssetAction, assetTicker: String):
        Result<Unit>
}
