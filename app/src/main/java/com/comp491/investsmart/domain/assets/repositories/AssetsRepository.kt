package com.comp491.investsmart.domain.assets.repositories

import com.comp491.investsmart.data.api.Result
import com.comp491.investsmart.domain.assets.entities.Asset
import com.comp491.investsmart.domain.assets.entities.AssetPrice

interface AssetsRepository {

    suspend fun getTrendingAssets(): Result<List<Asset>>
    suspend fun getAssetsWithKeyword(keyword: String): Result<List<Asset>>
    suspend fun getAssetPrices(assetTicker: String): Result<List<AssetPrice>>
    suspend fun getFavouriteAssets():Result<List<Asset>>
    suspend fun followAsset(assetTicker: String): Result<Unit>
    suspend fun unfollowAsset(assetTicker: String): Result<Unit>
}
