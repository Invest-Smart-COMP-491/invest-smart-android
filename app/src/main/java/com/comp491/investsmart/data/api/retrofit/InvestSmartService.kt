package com.comp491.investsmart.data.api.retrofit

import com.comp491.investsmart.data.assets.entities.AssetEntity
import com.comp491.investsmart.data.assets.entities.AssetPriceEntity
import com.comp491.investsmart.data.assets.entities.AssetTickerEntity
import com.comp491.investsmart.data.news.entities.NewsEntity
import com.comp491.investsmart.data.users.entities.LoginUserEntity
import com.comp491.investsmart.data.users.entities.RegisterUserEntity
import com.comp491.investsmart.domain.comments.entities.Comment
import retrofit2.Response
import retrofit2.http.*

interface InvestSmartService {

    @GET("/api/trending-stocks/news")
    suspend fun getNews(): Response<List<NewsEntity>>

    @GET("/api/trending-stocks/")
    suspend fun getTrendingAssets(): Response<List<AssetEntity>>

    @GET("/api/news/{assetTicker}")
    suspend fun getAssetNews(@Query("assetTicker") assetTicker: String): Response<List<NewsEntity>>

    @GET("/api/assets/{keyword}")
    suspend fun getAssetsWithKeyword(@Query("keyword") keyword: String): Response<List<AssetEntity>>

    @GET("/api/prices/{assetTicker}")
    suspend fun getAssetPrices(@Query("assetTicker") assetTicker: String):
            Response<List<AssetPriceEntity>>

    @GET("/api/comments/{assetTicker}")
    suspend fun getAssetComments(@Query("assetTicker") assetTicker: String):
            Response<List<Comment>>

    @POST("/api/register")
    suspend fun register(@Body user: RegisterUserEntity): Response<Unit>

    @POST("/api/login")
    suspend fun login(@Body user: LoginUserEntity): Response<Unit>

    @POST("/api/logout")
    suspend fun logout(
        @Header("Authorization") token: String,
        @Body string: String,
    ): Response<Unit>

    @GET("/api/favorite")
    suspend fun getFavouriteAssets(
        @Header("Authorization") token: String
    ): Response<List<AssetEntity>>

    @POST("/api/favorite")
    suspend fun followAsset(
        @Header("Authorization") token: String,
        @Body assetTicker: AssetTickerEntity,
    ): Response<List<AssetEntity>>

    @DELETE("/api/favorite")
    suspend fun unFollowAsset(
        @Header("Authorization") token: String,
        @Body assetTicker: AssetTickerEntity,
    ): Response<List<AssetEntity>>
}
