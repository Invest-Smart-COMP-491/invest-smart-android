package com.comp491.investsmart.data.api.retrofit

import com.comp491.investsmart.data.assets.entities.AssetEntity
import com.comp491.investsmart.data.assets.entities.AssetPriceEntity
import com.comp491.investsmart.data.news.entities.NewsEntity
import com.comp491.investsmart.data.news.entities.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface InvestSmartService {

    @GET("/api/news/")
    suspend fun getNews(): Response<List<NewsEntity>>

    @GET("/api/news/{assetTicker}")
    suspend fun getAssetNews(@Query("assetTicker") assetTicker: String): Response<List<NewsEntity>>

    @GET("/api/assets/")
    suspend fun getAssets(): Response<List<AssetEntity>>

    @GET("/api/assets/{keyword}")
    suspend fun getAssetsWithKeyword(@Query("keyword") keyword: String): Response<List<AssetEntity>>

    @GET("/api/assets/fav")
    suspend fun getFavouriteAssets(): Response<List<AssetEntity>>

    @GET("/api/prices/{assetTicker}")
    suspend fun getAssetPrices(@Query("assetTicker") assetTicker: String):
            Response<List<AssetPriceEntity>>

    @GET("posts")
    suspend fun listPost(@Query("userId") userId: String): Response<List<Post>>
}
