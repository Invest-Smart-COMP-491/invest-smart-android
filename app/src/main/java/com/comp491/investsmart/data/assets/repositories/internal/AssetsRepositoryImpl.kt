package com.comp491.investsmart.data.assets.repositories.internal

import com.comp491.investsmart.data.api.Result
import com.comp491.investsmart.data.api.retrofit.InvestSmartService
import com.comp491.investsmart.data.api.safeApiCall
import com.comp491.investsmart.data.assets.entities.AssetTickerEntity
import com.comp491.investsmart.data.assets.entities.toDomain
import com.comp491.investsmart.data.datastore.DataStoreManager
import com.comp491.investsmart.domain.assets.entities.Asset
import com.comp491.investsmart.domain.assets.entities.AssetPrice
import com.comp491.investsmart.domain.assets.repositories.AssetsRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class AssetsRepositoryImpl @Inject constructor(
    private val investSmartService: InvestSmartService,
    private val dataStoreManager: DataStoreManager,
): AssetsRepository {

    override suspend fun getTrendingAssets(): Result<List<Asset>> {
        val result = safeApiCall { investSmartService.getTrendingAssets() }

        return if (result is Result.Success) {
            Result.Success(data = result.data?.map { it.toDomain() }  ?: emptyList())
        } else {
            Result.Error(errorMessage = result.message ?: "Something went wrong")
        }
    }

    override suspend fun getFavouriteAssets(): Result<List<Asset>> {
        val result =  safeApiCall {
            investSmartService.getFavouriteAssets(
                token = dataStoreManager.token.first(),
            )
        }

        return if (result is Result.Success) {
            Result.Success(data = result.data?.map { it.toDomain() }  ?: emptyList())
        } else {
            Result.Error(errorMessage = result.message ?: "Something went wrong")
        }
    }

    override suspend fun followAsset(assetTicker: String): Result<Unit>{
        return safeApiCall {
            investSmartService.followAsset(
                token = dataStoreManager.token.first(),
                assetTicker = AssetTickerEntity(assetTicker = assetTicker)
            )
        }
    }

    override suspend fun unfollowAsset(assetTicker: String): Result<Unit> {
        return safeApiCall {
            investSmartService.unFollowAsset(
                token = dataStoreManager.token.first(),
                assetTicker = AssetTickerEntity(assetTicker = assetTicker)
            )
        }
    }

    override suspend fun getAssetsWithKeyword(keyword: String): Result<List<Asset>> {
        val result =  safeApiCall {
            investSmartService.getAssetsWithKeyword(keyword = keyword)
        }

        return if (result is Result.Success) {
            Result.Success(data = result.data?.map { it.toDomain() }  ?: emptyList())
        } else {
            Result.Error(errorMessage = result.message ?: "Something went wrong")
        }
    }

    override suspend fun getAssetPrices(assetTicker: String): Result<List<AssetPrice>> {
        val result =  safeApiCall {
            investSmartService.getAssetPrices(assetTicker = assetTicker)
        }

        return if (result is Result.Success) {
            Result.Success(data = result.data?.map { it.toDomain() }  ?: emptyList())
        } else {
            Result.Error(errorMessage = result.message ?: "Something went wrong")
        }
    }
}
