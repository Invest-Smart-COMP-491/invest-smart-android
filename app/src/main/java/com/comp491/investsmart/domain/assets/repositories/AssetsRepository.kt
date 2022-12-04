package com.comp491.investsmart.domain.assets.repositories

import com.comp491.investsmart.domain.assets.entities.Asset
import com.comp491.investsmart.domain.assets.entities.AssetPrice

interface AssetsRepository {

    suspend fun getAssets(): List<Asset>
    suspend fun getAssetsWithKeyword(keyword: String): List<Asset>
    suspend fun getFavouriteAssets(): List<Asset>
    suspend fun getAssetPrices(assetTicker: String): List<AssetPrice>
}
