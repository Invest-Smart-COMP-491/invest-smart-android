package com.comp491.investsmart.data.api.retrofit

import com.comp491.investsmart.data.assets.entities.AssetEntity
import com.comp491.investsmart.data.assets.entities.AssetPriceEntity
import com.comp491.investsmart.data.assets.entities.AssetTickerEntity
import com.comp491.investsmart.data.comments.entities.AddCommentEntity
import com.comp491.investsmart.data.comments.entities.CommentEntity
import com.comp491.investsmart.data.news.entities.NewsEntity
import com.comp491.investsmart.data.users.entities.LoginUserEntity
import com.comp491.investsmart.data.users.entities.RegisterUserEntity
import com.comp491.investsmart.data.users.entities.TokenEntity
import retrofit2.Response
import retrofit2.http.*

interface InvestSmartService {

    @GET("/api/trending-stocks/news")
    suspend fun getNews(): Response<List<NewsEntity>>

    @GET("/api/trending-stocks/")
    suspend fun getTrendingAssets(): Response<List<AssetEntity>>

    @GET("/api/news/{assetTicker}")
    suspend fun getAssetNews(@Path("assetTicker") assetTicker: String): Response<List<NewsEntity>>

    @GET("/api/assets")
    suspend fun getAssetsWithKeyword(@Query("search") keyword: String): Response<List<AssetEntity>>

    @GET("/api/assets/{assetTicker}")
    suspend fun getAsset(@Path("assetTicker") assetTicker: String): Response<AssetEntity>

    @GET("/api/prices/{assetTicker}")
    suspend fun getAssetPrices(@Path("assetTicker") assetTicker: String):
            Response<List<AssetPriceEntity>>

    @GET("/api/comments")
    suspend fun getAssetComments(
        @Query("asset_ticker") assetTicker: String?,
        @Query("parent_comment") commentParent: String?,
        @Query("user") userId: Int?
    ):
            Response<List<CommentEntity>>

    @GET("/api/comments")
    suspend fun getCommentById(@Query("comment_id") comment_id: Int):
            Response<CommentEntity>

    @POST("/api/comments/")
    suspend fun addComment(
        @Header("Authorization") token: String,
        @Body addCommentEntity: AddCommentEntity,
    ): Response<Unit>

    @GET("/api/comment-likes")
    suspend fun getUserLikedComments(
        @Query("user") userId: Int,
    ): Response<List<CommentEntity>>

    @PUT("/api/comment-likes")
    suspend fun likeComment(
        @Header("Authorization") token: String,
        @Query("comment_id") commentId: Int,
    ): Response<Unit>

    @DELETE("/api/comment-likes")
    suspend fun unlikeComment(
        @Header("Authorization") token: String,
        @Query("comment_id") commentId: Int,
    ): Response<Unit>

    @GET("/api/favorite")
    suspend fun getFavouriteAssets(
        @Header("Authorization") token: String
    ): Response<List<AssetEntity>>

    @POST("/api/favorite")
    suspend fun followAsset(
        @Header("Authorization") token: String,
        @Body assetTicker: AssetTickerEntity,
    ): Response<Unit>

    @DELETE("/api/favorite")
    suspend fun unFollowAsset(
        @Header("Authorization") token: String,
        @Body assetTicker: AssetTickerEntity,
    ): Response<Unit>

    @POST("/api/register/")
    suspend fun register(@Body user: RegisterUserEntity): Response<TokenEntity>

    @POST("/api/login/")
    suspend fun login(@Body user: LoginUserEntity): Response<TokenEntity>

    @POST("/api/logout/")
    suspend fun logout(@Header("Authorization") token: String): Response<Unit>
}
