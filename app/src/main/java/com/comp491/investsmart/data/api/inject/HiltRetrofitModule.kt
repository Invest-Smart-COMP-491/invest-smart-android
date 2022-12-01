package com.comp491.investsmart.data.api.inject

import com.comp491.investsmart.data.api.retrofit.InvestSmartService
import com.comp491.investsmart.data.api.retrofit.RetrofitBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class HiltRetrofitModule {

    companion object {
        @Provides
        fun provideInvestSmartService(): InvestSmartService {
            return RetrofitBuilder.getClient().create(InvestSmartService::class.java)
        }
    }
}
