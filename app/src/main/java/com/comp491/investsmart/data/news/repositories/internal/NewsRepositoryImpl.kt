package com.comp491.investsmart.data.news.repositories.internal

import com.comp491.investsmart.data.api.retrofit.InvestSmartService
import com.comp491.investsmart.data.api.safeApiCall
import com.comp491.investsmart.data.api.Result
import com.comp491.investsmart.data.news.entities.toDomain
import com.comp491.investsmart.domain.news.entities.News
import com.comp491.investsmart.domain.news.repositories.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val investSmartService: InvestSmartService,
) : NewsRepository {

    override suspend fun getAllNews(): Result<List<News>> {
        val result = safeApiCall { investSmartService.getNews() }

        return if (result is Result.Success) {
            Result.Success(data = result.data?.map { it.toDomain() }  ?: emptyList())
        } else {
            Result.Error(errorMessage = result.message ?: "Something went wrong")
        }
    }

    override suspend fun getAssetNews(assetTicker: String): Result<List<News>> {
        val result = safeApiCall { investSmartService.getAssetNews(assetTicker = assetTicker) }

        return if (result is Result.Success) {
            Result.Success(data = result.data?.map { it.toDomain() }  ?: emptyList())
        } else {
            Result.Error(errorMessage = result.message ?: "Something went wrong")
        }
    }
}
