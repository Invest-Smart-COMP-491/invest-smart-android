package com.comp491.investsmart.data.news.inject

import com.comp491.investsmart.data.news.repositories.internal.NewsRepositoryImpl
import com.comp491.investsmart.domain.news.repositories.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class HiltNewsModule {

    @Binds
    abstract fun bindNewsRepository(
        newsRepositoryImpl: NewsRepositoryImpl
    ): NewsRepository
}
