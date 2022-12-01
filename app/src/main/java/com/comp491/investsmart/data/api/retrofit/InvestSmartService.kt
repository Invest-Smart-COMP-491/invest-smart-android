package com.comp491.investsmart.data.api.retrofit

import com.comp491.investsmart.data.news.entities.NewsEntity
import com.comp491.investsmart.data.news.entities.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface InvestSmartService {

    @GET("news/")
    suspend fun getNews(): Response<List<NewsEntity>>

    @GET("posts")
    suspend fun listPost(@Query("userId") userId: String): Response<List<Post>>
}
