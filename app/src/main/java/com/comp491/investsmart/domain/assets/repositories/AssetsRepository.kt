package com.comp491.investsmart.domain.assets.repositories

import com.comp491.investsmart.domain.assets.entities.Asset
import com.comp491.investsmart.domain.assets.entities.AssetPrice

interface AssetsRepository {

    suspend fun getAssetPrices(assetTicker: String): List<AssetPrice>
    suspend fun getAllAssetPrices(): List<AssetPrice>
    suspend fun getAllFavouriteAssets(): List<Asset>
    suspend fun getAllAssets(): List<Asset>
    suspend fun getAssetsWithKeyword(keyword: String): List<Asset>
}
