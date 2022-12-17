package com.comp491.investsmart.data.assets.repositories.internal

import com.comp491.investsmart.data.api.Result
import com.comp491.investsmart.data.api.retrofit.InvestSmartService
import com.comp491.investsmart.data.api.safeApiCall
import com.comp491.investsmart.data.assets.entities.toDomain
import com.comp491.investsmart.domain.assets.entities.Asset
import com.comp491.investsmart.domain.assets.entities.AssetPrice
import com.comp491.investsmart.domain.assets.repositories.AssetsRepository
import javax.inject.Inject

class AssetsRepositoryImpl @Inject constructor(
    private val investSmartService: InvestSmartService,
): AssetsRepository {

    override suspend fun getTrendingAssets(): Result<List<Asset>> {
        val result = safeApiCall { investSmartService.getTrendingAssets() }

        return if (result is Result.Success) {
            Result.Success(data = result.data?.map { it.toDomain() }  ?: emptyList())
        } else {
            Result.Error(errorMessage = result.message ?: "Something went wrong")
        }
    }

    override suspend fun getAssetsWithKeyword(keyword: String): List<Asset> {
        TODO("Not yet implemented")
    }

    override suspend fun getFavouriteAssets(): List<Asset> {
        TODO("Not yet implemented")
    }

    override suspend fun getAssetPrices(assetTicker: String): List<AssetPrice> {
        TODO("Not yet implemented")
    }
}
