package com.comp491.investsmart.data.assets.repositories.internal

import com.comp491.investsmart.domain.assets.entities.Asset
import com.comp491.investsmart.domain.assets.entities.AssetPrice
import com.comp491.investsmart.domain.assets.repositories.AssetsRepository
import javax.inject.Inject

class AssetsRepositoryImpl @Inject constructor(

): AssetsRepository {
    override suspend fun getAssetPrices(assetTicker: String): List<AssetPrice> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllAssetPrices(): List<AssetPrice> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllFavouriteAssets(): List<Asset> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllAssets(): List<Asset> {
        TODO("Not yet implemented")
    }
}
